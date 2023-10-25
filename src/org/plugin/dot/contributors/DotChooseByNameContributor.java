package org.plugin.dot.contributors;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.plugin.dot.DotPSITreeUtil;
import org.plugin.dot.psi.DotId;

import java.util.ArrayList;
import java.util.List;

public class DotChooseByNameContributor implements ChooseByNameContributor {
  @NotNull
  @Override
  public String @NotNull [] getNames(Project project, boolean includeNonProjectItems) {
    Iterable<DotId> properties = DotPSITreeUtil.findDotIds(project);
    List<String> names = new ArrayList<String>();
    for (DotId property : properties) {
      if (property!=null) {
        names.add(property.getText());
      }
    }
    return names.toArray(new String[0]);
  }

  @NotNull
  @Override
  public NavigationItem @NotNull [] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
    // todo include non project items
      return DotPSITreeUtil.findDotIds(project, name).stream().toList().toArray(new NavigationItem[0]);
  }
}
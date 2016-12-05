package org.plugin.dot.contributors;

import com.intellij.navigation.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.plugin.dot.DotPSITreeUtil;
import org.plugin.dot.psi.DotId;

import java.util.*;

public class DotChooseByNameContributor implements ChooseByNameContributor {
  @NotNull
  @Override
  public String[] getNames(Project project, boolean includeNonProjectItems) {
    Iterable<DotId> properties = DotPSITreeUtil.findDotIds(project);
    List<String> names = new ArrayList<String>();
    for (DotId property : properties) {
      if (property!=null) {
        names.add(property.getText());
      }
    }
    return names.toArray(new String[names.size()]);
  }

  @NotNull
  @Override
  public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
    // todo include non project items
    List<DotId> properties = (List<DotId>) DotPSITreeUtil.findDotIds(project, name);
    return properties.toArray(new NavigationItem[properties.size()]);
  }
}
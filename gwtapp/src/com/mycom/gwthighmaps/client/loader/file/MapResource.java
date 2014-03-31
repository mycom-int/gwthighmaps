package com.mycom.gwthighmaps.client.loader.file;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface MapResource extends ClientBundle
{
   public static final MapResource INSTANCE = GWT.create(MapResource.class);

   @Source("usastates_map.json")
   public TextResource map();

   @Source("usastates_mapdata.json")
   public TextResource mapData();

   @Source("usastates_mapline.json")
   public TextResource mapline();

}

package com.mycom.gwthighmaps.client.loader;

import javax.annotation.CheckForNull;

import com.google.gwt.json.client.JSONObject;

public interface Loader
{
   public void init();

   public JSONObject getMap();

   @CheckForNull
   public JSONObject getMapLine();

   @CheckForNull
   public JSONObject getMapData();
}

package com.mycom.gwthighmaps.client.loader.file;

import javax.annotation.CheckForNull;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.json.client.JSONObject;
import com.mycom.gwthighmaps.client.loader.Loader;

public class FileLoader implements Loader
{

   @Override
   public void init()
   {}

   @Override
   public JSONObject getMap()
   {
      JavaScriptObject map = JsonUtils.safeEval(MapResource.INSTANCE.map().getText());
      JSONObject m = new JSONObject(map);
      return m;
   }

   @CheckForNull
   @Override
   public JSONObject getMapData()
   {
      JavaScriptObject map = JsonUtils.safeEval(MapResource.INSTANCE.mapData().getText());
      JSONObject m = new JSONObject(map);
      return m;
   }

   @Override
   public JSONObject getMapLine()
   {
      JavaScriptObject map = JsonUtils.safeEval(MapResource.INSTANCE.mapline().getText());
      JSONObject m = new JSONObject(map);
      return m;
   }

}

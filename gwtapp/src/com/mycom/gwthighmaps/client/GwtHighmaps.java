package com.mycom.gwthighmaps.client;

import org.moxieapps.gwt.highcharts.client.BaseChart;
import org.moxieapps.gwt.highcharts.client.Series;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.mycom.gwthighmaps.client.loader.Loader;
import com.mycom.gwthighmaps.client.loader.file.FileLoader;
import com.mycom.gwthighmaps.client.mapwrapper.ChartSimpleLayoutPanel;
import com.mycom.gwthighmaps.client.mapwrapper.Map;

public class GwtHighmaps implements EntryPoint
{

   @Override
   public void onModuleLoad()
   {
      startInDockLayoutPanel();
   }

   public void startInDockLayoutPanel()
   {
      final RootLayoutPanel rootPanel = RootLayoutPanel.get();

      final DockLayoutPanel dock1 = new DockLayoutPanel(Unit.PX);

      SimpleLayoutPanel slp1 = new SimpleLayoutPanel();
      slp1.getElement().getStyle().setBackgroundColor("blue");

      SimpleLayoutPanel slp2 = new SimpleLayoutPanel();
      slp2.getElement().getStyle().setBackgroundColor("yellow");

      dock1.addNorth(slp1, 50);
      dock1.addWest(slp2, 50);

      final BaseChart<?> chart = createChart();
      ChartSimpleLayoutPanel cp = new ChartSimpleLayoutPanel(chart);

      dock1.add(cp);

      rootPanel.add(dock1);

      Scheduler scheduler = Scheduler.get();
      scheduler.scheduleDeferred(new ScheduledCommand()
      {

         @Override
         public void execute()
         {
            chart.setSizeToMatchContainer();
         }
      });
   }

   public BaseChart<?> createChart()
   {
      Loader loader = new FileLoader();

      final Map map = new Map();

      setColorAxis(map);

      setNavigation(map);

      setSeries(loader, map);

      return map;
   }

   private void setSeries(Loader loader, final Map map)
   {
      // Add states
      Series states = map.createSeries().setName("Network Health");
      states.setOption("/data", loader.getMap());

      if (loader.getMapData() != null)
         states.setOption("/mapData", loader.getMapData());

      states.setOption("/joinBy", "code");
      states.setOption("/borderColor", "#7E7E7E");
      states.setOption("/states/hover/color", "#009deb");
      states.setOption("/dataLabels/enabled", true);
      states.setOption("/dataLabels/color", "black");
      states.setOption("/dataLabels/format", "{point.code}");

      // map.setOption("/tooltip/formatter", "function(){return '<b>{point.fullname}:</b> {point.value}';}");
      // map.setToolTip(new ToolTip().setFormatter(new ToolTipFormatter()
      // {
      //
      // @Override
      // public String format(ToolTipData toolTipData)
      // {
      // return "<b>{point.fullname}:</b> {point.value}";
      // }
      // }));

      map.addSeries(states);

      // Add Alaska/Hawaii lines
      Series mapLine = map.createSeries().setName("Separator");
      mapLine.setOption("/type", "mapline");
      mapLine.setOption("/color", "black");

      if (loader.getMapLine() != null)
         mapLine.setOption("/data", loader.getMapLine());

      map.addSeries(mapLine);
   }

   private void setNavigation(final Map map)
   {
      map.setOption("/mapNavigation/enabled", true);
      map.setOption("/mapNavigation/buttonOptions/verticalAlign", "bottom");
   }

   private void setColorAxis(final Map map)
   {
      // map.setOption("/colorAxis/min", 1);
      // map.setOption("/colorAxis/max", 1000);
      // map.setOption("/colorAxis/type", "logarithmic");

      String dataClasses = "[{\"from\": 0,\"to\": 6,\"color\": \"#FF6666\"}, {\"from\": 6,\"to\": 8,\"color\": \"#FFC266\"}, {\"from\": 8,\"to\": 10,\"color\": \"#B5FF6C\"}]";
      map.setOption("/colorAxis/dataClasses", JsonUtils.safeEval(dataClasses));
   }
}

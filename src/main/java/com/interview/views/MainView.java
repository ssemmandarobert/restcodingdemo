package com.interview.views;

import com.interview.dao.MessageRepository;
import com.interview.model.MessageModel;
import com.interview.model.MessageStatics;
import com.interview.services.BroadCaster;
import com.interview.services.MesssageService;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.navigator.View;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert.j.ssemmanda on 29/07/2017.
 * This is the main view for the user when the view (home path)
 */
@SpringView(name = MainView.VIEW_NAME)
@VaadinSessionScope
public class MainView extends VerticalLayout  implements View, BroadCaster.BroadcastListener {
    public static final String VIEW_NAME = "";

    /*
    * Local Variables
    * */
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MesssageService messageService;

    //UI elements
    private Grid grid;

    //graphs
    private Chart countFromChart, countToChart, originCountry, userId;


    /*
    * Default constructors*/
    public MainView(){
       // init();
    }

    /*
    * we use this method to setup the class
    * */
    @PostConstruct
    private void init(){
        setSizeFull();

        //initialize our data provider
        //register to receive broadcasts
        BroadCaster.register(this);

        Label titleLable = new Label("Messages Dashboard");
        titleLable.setStyleName(ValoTheme.LABEL_H2);
        titleLable.addStyleName(ValoTheme.LABEL_LIGHT);
        titleLable.setWidth(80.0f, Unit.PERCENTAGE);

        //grid to show the results
        grid = new Grid<>(MessageModel.class);
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.setSizeFull();

        //data provider to setup paging for lots of data
        grid.setDataProvider(
                (sortOrders, offset, limit) ->
                        messageService.findAll(offset,limit).stream(),
                () -> messageService.count()
        );

        //create layout for 1st graphs
        HorizontalLayout panelOne = new HorizontalLayout();
        panelOne.setSizeFull();

        countFromChart = new Chart(ChartType.COLUMN);
        Configuration configuration = countFromChart.getConfiguration();
        configuration.setTitle("Currency From -> To Count");
        List<MessageStatics>stats = messageRepository.findCurrencyFromToCount();
        //set charts data
        final List<Series>listSeries = new ArrayList<>();
        stats.forEach(messageStatics -> {
            ListSeries series = new ListSeries(messageStatics.getName()+" -> "+messageStatics.getSecondName());
            series.addData(messageStatics.getStat());
            listSeries.add(series);
        });
        configuration.setSeries(listSeries);

        countToChart = new Chart(ChartType.COLUMN);
        configuration = countToChart.getConfiguration();
        configuration.setTitle("Currency From -> To Average Rate");
        stats = messageRepository.findCurrencyFromToSumAvgRate();
        //set charts data
        final List<Series>list2Series = new ArrayList<>();
        stats.forEach(messageStatics -> {
            ListSeries series = new ListSeries(messageStatics.getName()+" -> "+messageStatics.getSecondName());
            series.addData(messageStatics.getCurrencyAmounts());
            list2Series.add(series);
        });
        configuration.setSeries(list2Series);


        //create layout for 2nd graphs
        HorizontalLayout panelTwo = new HorizontalLayout();
        panelTwo.setSizeFull();

        originCountry = new Chart(ChartType.COLUMN);
        configuration = originCountry.getConfiguration();
        configuration.setTitle("Origin Country Count");
        stats = messageRepository.findCountryCount();
        //set charts data
        final List<Series>list3Series = new ArrayList<>();
        stats.forEach(messageStatics -> {
            ListSeries series = new ListSeries(messageStatics.getName());
            series.addData(messageStatics.getStat());
            list3Series.add(series);
        });
        configuration.setSeries(list3Series);

        userId = new Chart(ChartType.COLUMN);
        configuration = userId.getConfiguration();
        configuration.setTitle("User ID Count");
        stats = messageRepository.findUserIdTransCount();
        //set charts data
        final List<Series>list4Series = new ArrayList<>();
        stats.forEach(messageStatics -> {
            ListSeries series = new ListSeries(messageStatics.getName());
            series.addData(messageStatics.getStat());
            list4Series.add(series);
        });
        configuration.setSeries(list4Series);

        panelOne.addComponentsAndExpand(countFromChart,countToChart);
        panelTwo.addComponentsAndExpand(originCountry,userId);

        //tell graphs to draw
        countFromChart.drawChart();
        countToChart.drawChart();
        originCountry.drawChart();
        userId.drawChart();

        addComponentsAndExpand(titleLable,panelOne,panelTwo,grid);




    }

    //this method is called when the view is being removed from the ui
    @Override
    public void detach(){
        BroadCaster.unregister(this);
        super.detach();
    }


    //implement our method to receive a broadcast
    @Override
    public void receiveBroadcast(Object messageModel) {
        // Must lock the session to execute logic safely
        getUI().access(new Runnable() {
            @Override
            public void run() {
                grid.getDataProvider().refreshAll();
                updateConfiguration(countFromChart.getConfiguration(),messageRepository.findCurrencyFromToCount());
                updateConfiguration(countToChart.getConfiguration(),messageRepository.findCurrencyFromToSumAvgRate());
                updateConfiguration(originCountry.getConfiguration(),messageRepository.findCountryCount());
                updateConfiguration(userId.getConfiguration(),messageRepository.findUserIdTransCount());
                countFromChart.drawChart();
                countToChart.drawChart();
                originCountry.drawChart();
                userId.drawChart();
            }
        });
    }

    public MessageRepository getMessageRepository() {
        return messageRepository;
    }

    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    //create graphs
    private void updateConfiguration(Configuration configuration, List<MessageStatics> messageStatics){
       //data for the graphs
        List<Series>listSeries = new ArrayList<>();
        messageStatics.forEach(messageStatics1 -> {
            String serieName = messageStatics1.getName();
            //check for second grouping
            String secondName = messageStatics1.getSecondName();
            serieName = !(secondName == null || secondName.trim().isEmpty())?serieName.concat(" -> "+ secondName):serieName;

            ListSeries series = new ListSeries(serieName);
            //check which of the two was populate i.e currencyamounts or stats
            //currency amounts is for double amounts while stats is for integer amounts
            series.addData(messageStatics1.getStat() == null?
                    messageStatics1.getCurrencyAmounts(): messageStatics1.getStat());
            //add to series
            listSeries.add(series);
        });
        configuration.setSeries(listSeries);
    }

}

package org.example;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Root {

    @JsonProperty("england-and-wales")
    public EnglandAndWales ew;
    @JsonProperty("scotland")
    public Scotland scotland;
    @JsonProperty("northern-ireland")
    public NorthernIreland ni;

    public Events getAllEvents(Region[] regions) {
        Events events = new Events();

        if (regions == null || regions.length == 0) {
            regions = new Region[] { Region.ALL };
        }

        for (Region r : regions) {
            if (r == Region.ALL) {
                regions = new Region[] { Region.ALL };
                break;
            }
        }

        for (Region r : regions) {
            switch (r) {
                case SCOTLAND:
                    events.addAll(scotland.events);
                    break;
                case ENGLAND_AND_WALES:
                    events.addAll(ew.events);
                    break;
                case NORTHERN_IRELAND:
                    events.addAll(ni.events);
                    break;
                default:
                    events.addAll(scotland.events);
                    events.addAll(ew.events);
                    events.addAll(ni.events);
                    break;
            }
        }

        return events;
    }


}

package atria.communities.cia;


import com.google.firebase.Timestamp;

public class EventModel {

    String eventid
    ,eventtitle
    ,eventshortdescription
    ,domainid
    ,eventlocation
    ,eventhost
    ,eventdescription
    ,eventduration
    ,eventprerequisite
    ,eventbenefits
    ,eventcontactnumber;

    Timestamp eventtimestamp;


    public EventModel(String eventid, String eventtitle, String eventshortdescription, String domainid, String eventlocation, String eventhost, String eventdescription, String eventduration, String eventprerequisite, String eventbenefits, String eventcontactnumber, Timestamp eventtimestamp) {
        this.eventid = eventid;
        this.eventtitle = eventtitle;
        this.eventshortdescription = eventshortdescription;
        this.domainid = domainid;
        this.eventlocation = eventlocation;
        this.eventhost = eventhost;
        this.eventdescription = eventdescription;
        this.eventduration = eventduration;
        this.eventprerequisite = eventprerequisite;
        this.eventbenefits = eventbenefits;
        this.eventcontactnumber = eventcontactnumber;
        this.eventtimestamp = eventtimestamp;
    }

    public String getEventid() {
        return eventid;
    }

    public String getEventtitle() {
        return eventtitle;
    }

    public String getEventshortdescription() {
        return eventshortdescription;
    }

    public String getDomainid() {
        return domainid;
    }

    public String getEventlocation() {
        return eventlocation;
    }

    public String getEventhost() {
        return eventhost;
    }

    public String getEventdescription() {
        return eventdescription;
    }

    public String getEventduration() {
        return eventduration;
    }

    public String getEventprerequisite() {
        return eventprerequisite;
    }

    public String getEventbenefits() {
        return eventbenefits;
    }

    public String getEventcontactnumber() {
        return eventcontactnumber;
    }


    public Timestamp getEventtimestamp() {
        return eventtimestamp;
    }
}
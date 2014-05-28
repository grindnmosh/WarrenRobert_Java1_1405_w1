package com.grindesign.listView;

//Robert Warren
//Java 1
//Term 1405


public class enumFile {
    //create enum lo create JSON Object from
    public enum Feeds {
        feed1("Going to dinner at Subway", "5/11/2014 05:30 PM"),
        feed2("Going to the Gym", "5/11/2014 07:30 PM"),
        feed3("Homework Grind", "5/12/2014 07:38 AM"),
        feed4("Struggling with Enums and Classes", "5/13/2014 07:06 AM"),
        feed5("OMG the season finale of S.H.I.E.L.D. was amazing", "5/13/2014 09:33 PM"),
        feed6("Date Night", "5/14/2014 04:33 PM");


        //define strings to pull out
        private String posting;
        private String dated;

        //define string placement in the enum
        private Feeds(String f, String d) {
            this.posting = f;
            this.dated = d;

        }

        //set and return the posting
        public String setPosting() {
            return posting;
        }

        //set and return the date
        public String setDated() {
            return dated;
        }



    }


}

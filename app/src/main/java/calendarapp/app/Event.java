package calendarapp.app;

import java.time.LocalDate;
import java.time.LocalTime;

public interface Event
{
    public LocalDate getDate();
    public String getTitle();
    
    public void setDate(LocalDate date);
    public void setTitle(String title);
    

    // [ All-Day Event ]
    
    class AllDayEvent implements Event
    {
        private LocalDate date;
        private String title;

        public AllDayEvent(LocalDate date, String title)
        {
            this.date = date;
            this.title = title;
        }
        
        
        @Override
        public LocalDate getDate()
        {
            return date;
        }
        
        @Override
        public String getTitle()
        {
            return title;
        }
        
        
        @Override
        public void setDate(LocalDate date)
        {
            this.date = date;
        }
        
        @Override
        public void setTitle(String title)
        {
            this.title = title;
        }
    }
    

    // [ Hourly Event ]

    class HourlyEvent implements Event
    {
        private LocalDate date;
        private String title;
        private LocalTime startTime;
        private int durationMins;

        public HourlyEvent(LocalDate date, String title, LocalTime startTime, int durationMins)
        {
            this.date = date;
            this.title = title;
            this.startTime = startTime;
            this.durationMins = durationMins;
        }
        

        @Override
        public LocalDate getDate()
        {
            return date;
        }
        
        @Override
        public String getTitle()
        {
            return title;
        }
        
        public LocalTime getStartTime()
        {
            return startTime;
        }
        
        public int getDurationMins()
        {
            return durationMins;
        }
        
        
        @Override
        public void setDate(LocalDate date)
        {
            this.date = date;
        }
        
        @Override
        public void setTitle(String title)
        {
            this.title = title;
        }
        
        public void setStartTime(LocalTime startTime)
        {
            this.startTime = startTime;
        }
        
        public void setDurationMins(int durationMins)
        {
            this.durationMins = durationMins;
        }
    }
}

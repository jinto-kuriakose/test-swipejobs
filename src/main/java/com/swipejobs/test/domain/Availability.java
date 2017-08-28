package com.swipejobs.test.domain;

public class Availability {

    private String title;
    private int dayIndex;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Availability that = (Availability) o;

        return dayIndex == that.dayIndex;
    }

    @Override
    public int hashCode() {
        return dayIndex;
    }

    @Override
    public String toString() {
        return "Availability{" +
                "title='" + title + '\'' +
                ", dayIndex=" + dayIndex +
                '}';
    }
}

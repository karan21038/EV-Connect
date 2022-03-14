package com.example.auth_app.ui.stations;

public class StationListItem {
    private boolean status;
    public int index;

    public StationListItem(int index, boolean status)
    {
        this.index = index;
        this.status = status;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean getStatus()
    {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}


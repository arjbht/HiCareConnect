package com.ab.hicarecommercialapp.location_service.listner;

import android.location.Location;

public interface LocationManagerListner {
  String TAG = LocationManagerListner.class.getSimpleName();

  void locationFetched(Location mLocation, Location oldLocation, String time,
                       String locationProvider);
}

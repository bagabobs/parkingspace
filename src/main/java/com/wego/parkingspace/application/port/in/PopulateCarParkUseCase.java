package com.wego.parkingspace.application.port.in;

import com.wego.parkingspace.exceptions.PopulateException;

public interface PopulateCarParkUseCase {
    int populate() throws PopulateException;
}

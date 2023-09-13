package com.wego.parkingspace.application.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Svy21Coordinate(@JsonProperty("X") double xCoordinate, @JsonProperty("Y") double yCoordinate) { }

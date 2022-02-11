package com.fl.ml.request;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fl.ml.model.Satellite;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"satellites"})
@Generated("jsonschema2pojo")
public class InputRequest {

@JsonProperty("satellites")
private List<Satellite> satellites = null;

@JsonProperty("satellites")
public List<Satellite> getSatellites() {
return satellites;
}

@JsonProperty("satellites")
public void setSatellites(List<Satellite> satellites) {
this.satellites = satellites;
}

}

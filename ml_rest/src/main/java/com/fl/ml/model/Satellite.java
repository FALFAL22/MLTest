package com.fl.ml.model;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"name",
"distance",
"message"
})
@Generated("jsonschema2pojo")
public class Satellite implements Serializable{

private static final long serialVersionUID = 1L;

@JsonProperty("name")
private String name;
@JsonProperty("distance")
private Double distance;
@JsonProperty("message")
private List<String> message = null;

@JsonProperty("name")
public String getName() {
return name;
}
@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("distance")
public Double getDistance() {
return distance;
}

@JsonProperty("distance")
public void setDistance(Double distance) {
this.distance = distance;
}

@JsonProperty("message")
public List<String> getMessage() {
return message;
}

@JsonProperty("message")
public void setMessage(List<String> message) {
this.message = message;
}

}
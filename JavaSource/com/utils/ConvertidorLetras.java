package com.utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("convertidor")
public class ConvertidorLetras implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		return (submittedValue != null) ? submittedValue.toLowerCase() : null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
		return (modelValue != null) ? modelValue.toString() : "";
	}

}
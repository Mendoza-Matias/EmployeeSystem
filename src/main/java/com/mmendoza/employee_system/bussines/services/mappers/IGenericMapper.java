package com.mmendoza.employee_system.bussines.services.mappers;

import java.util.List;

public interface IGenericMapper<T, Y> {

    Y toDto(T t);

    List <Y> toDtoList(List<T> t);

}

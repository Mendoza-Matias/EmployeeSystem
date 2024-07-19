package com.mmendoza.employee_system.bussines.mappers;

import java.util.List;

public interface GenericMapper <E,D> {

    D toDTO(E ent);

    List <D> toDTOAList(List<E> ent);

}

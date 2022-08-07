package com.example.nytimescleanarchitecture.domain.model.mapper

interface DataToDomainMapper<in dataObject, out domainObject> {
    fun mapDataToDomainObject(obj: dataObject): domainObject
}
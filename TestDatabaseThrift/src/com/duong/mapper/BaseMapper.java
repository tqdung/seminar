/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duong.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author hoang
 * @param <T> entity type
 * @param <R> result/DTO type
 */
public interface BaseMapper<T, R> {
    
    R entity2Dto(T t);
    
    R entity2Dto(T t, R r);
    
    T dto2Entity(R r);
    
    T dto2Entity(R r, T t);
    
    default List<R> entities2Dtos(List<T> t) {
        
        List<R> result = new ArrayList<>();
        for (T _t : t) {
            R r = entity2Dto(_t);
            result.add(r);
        }
        return result;
//        return t.stream().map(_t -> entity2Dto(_t))
//                .collect(Collectors.toList());
    }
    
    default List<T> dtos2Entities(List<R> r) {
        List<T> result = new ArrayList<>();
        for (R _r : r) {
            T t = dto2Entity(_r);
            result.add(t);
        }
        return result;
    }
    
}

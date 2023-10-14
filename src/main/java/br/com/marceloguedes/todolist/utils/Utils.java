package br.com.marceloguedes.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
    //Vai copiar os atributos com valor de um objeto para outro.
    public static void copyNonNullPropertyNames(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropityNames(source));
    }
    
    public static String[] getNullPropityNames(Object source){
        //é uma interface fornece uma forma de acessar as propriedades de um objeto
        final BeanWrapper src = new BeanWrapperImpl(source);
        
         
         //Definindo o atributo pds como um array de propriedades do objeto source
         PropertyDescriptor[] pds = src.getPropertyDescriptors();
         
         
         Set<String> emptyNames = new HashSet<>();
         
         //Para cada propiedade dentro de pds ele verifica se o valor da propriedade é nulo, caso seja ele adicona alista de emptyNames
         for(PropertyDescriptor pd: pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if(srcValue == null) {
                emptyNames.add(pd.getName());
            }
         }

          //criando uma lista com os atributos nulos
          String[] result = new String[emptyNames.size()];
          System.out.println("result: "+ emptyNames);
          return emptyNames.toArray(result);

    }
}

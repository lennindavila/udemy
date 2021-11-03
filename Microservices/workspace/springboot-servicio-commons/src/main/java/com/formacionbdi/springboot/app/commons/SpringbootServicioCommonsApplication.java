package com.formacionbdi.springboot.app.commons;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//sobreescribimos @EnableAutoConfiguration excluyendo la cutoconfiguracion del datasource ya que del pom eliminamos la dependencia al H2, 
//usualmente esta anotacion ya esta incluida en la anotacion de arriba pero en nuestro caso la sobreescribimos para hacer esa exclusion
@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
public class SpringbootServicioCommonsApplication {


}

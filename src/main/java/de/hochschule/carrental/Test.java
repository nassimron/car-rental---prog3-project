package de.hochschule.carrental;

import de.hochschule.carrental.data.tables.records.CarsRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.concurrent.*;

import static de.hochschule.carrental.data.Tables.CARS;


public class Test {


    DSLContext dsl = DSL.using(App.con, SQLDialect.SQLITE);

    public void findCars(){
        Result<CarsRecord> cars = dsl.selectFrom(CARS).fetch();

        for(CarsRecord car : cars){
            System.out.println(car.getId() + " | " + car.getBrand() + " | " + car.getModel());
        }
    }


}

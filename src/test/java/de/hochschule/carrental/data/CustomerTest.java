package de.hochschule.carrental.data;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

    private Customer customer;

  @BeforeEach
    void setUP() {
      customer = new Customer("U001", "Barack Obama", "DL12345", "barack.obama@gmail.com");
  }

  @Test
    void testConstructorAndGetters() {
      assertEquals("U001", customer.getID());
      assertEquals("Barack Obama", customer.getName());
      assertEquals("DL12345", customer.getDLNumber());
      assertEquals("barack.obama@gmail.com", customer.getEmail());
  }

 @Test
 void testSetID(){
      customer.setID("U002");
      assertEquals("002", customer.getID());
 }

 @Test
    void testSetName() {
      customer.setName("Angela Merkel");
      assertEquals("Angela Merkel", customer.getName());
 }

 @Test
    void testSetDLNumber() {
      customer.setDLNumber("DL54321");
      assertEquals("DL54321", customer.getDLNumber());
 }

 @Test
    void testSetEmail() {
      customer.setEmail("angela.merkel@gmail.com");
      assertEquals("angela.merkel@gmail.com", customer.getEmail());
 }
}

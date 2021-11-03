package org.ldavila.junit5app.ejemplos.models;

//import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.ldavila.junit5app.ejemplos.exceptions.DineroInsuficienteException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CuentaTest {
    Cuenta cuenta;

    public CuentaTest(){
        System.out.println("Instancia...");
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("Iniciando el test.");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("Finalizando el test.");
    }

    @BeforeEach
    void initMetodoTest(){
        this.cuenta = new Cuenta("Lennin", new BigDecimal(1000.12345));
        System.out.println("Iniciando el metodo. " + this.hashCode());
    }

    @AfterEach
    void tearDown() {
        System.out.println("Finalizando el metodo. " +  + this.hashCode());
    }

    @Test
    @DisplayName("Probando nombre de la cuenta corriente!")
    void testNombreCuenta() {
        //Cuenta cuenta = new Cuenta("Lennin", new BigDecimal(1000.12345)); //lo llevamos como atributo de clase para probar el @BeforeEach
//        cuenta.setPersona("Lennin");
        String esperado = "Lennin";
        String real = cuenta.getPersona();

        /*
        Las cadenas de mensajes en los assert pueden consumir recursos si solo se agrega la cadena sola "este mensaje" pero en Junit5 permite hacerlo de amenra eficiente
        simplemente agregando una expresion lambda "() ->", asi no tendra que instanciar la cadena siempre, si no solo cuando es necesario hacerlo,
        el codigo cambiariaria de
        assertEtc(valor1,valor2,"mensaje");
        por
        assertEtc(valor,valor2, () -> "mensaje");
        * */

        assertNotNull(real,() ->"La cuenta no puede ser nula");
        assertEquals(esperado, real, () ->"el nombre de la cuenta no es el que se esperaba " + esperado + " sin embargo fue " + real);
        assertTrue(real.equals("Lennin"),() ->"nombre cuenta esperada debe ser igual a la real");
    }

    @Test
    @DisplayName("Probando el saldo de la cuenta, que no sea null, mayor que cero, valor esperado.")
    void testSaldoCuenta() {
        //Cuenta cuenta = new Cuenta("Lennin", new BigDecimal("1000.12345"));
        //this.cuenta = new Cuenta("Lennin", new BigDecimal("1000.12345")); //lo llevamos como atributo de clase para probar el @BeforeEach
        assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    @DisplayName("testeando referencias que sean iguales.")
    void testReferenciaCuenta() {
        //Cuenta cuenta = new Cuenta("Jhon Doe", new BigDecimal("8900.9997"));
        this.cuenta = new Cuenta("Jhon Doe", new BigDecimal("8900.9997")); //lo llevamos como atributo de clase para probar el @BeforeEach
        Cuenta cuenta2 = new Cuenta("Jhon Doe", new BigDecimal("8900.9997"));

        //assertNotEquals(cuenta2,cuenta);
        assertEquals(cuenta2, cuenta);
    }

    @Test
    void testDineroInsuficienteExceptionCuenta() {
        //Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        this.cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));//lo llevamos como atributo de clase para probar el @BeforeEach
        Exception exception = assertThrows(DineroInsuficienteException.class, () -> {
            cuenta.debito(new BigDecimal(1500));
        });
        String actual = exception.getMessage();
        String esperado = "Dinero Insuficiente";

        assertEquals(esperado, actual);
    }

    @Test
    void testTransferirDineroCuentas() {
        Cuenta cuenta1 = new Cuenta("Jhon Doe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Andres", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.setNombre("Banco del Estado");
        banco.transferir(cuenta2, cuenta1, new BigDecimal(500));
        assertEquals("1000.8989", cuenta2.getSaldo().toPlainString());
        assertEquals("3000", cuenta1.getSaldo().toPlainString());

    }

    @Test
    @Disabled
    @DisplayName("probando relaciones entre las cuentas y el banco con assertAll")
    void testRelacionBancoCuentas() {
        fail(); //forzamos un error
        Cuenta cuenta1 = new Cuenta("Jhon Doe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Andres", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.addCuenta(cuenta1);
        banco.addCuenta(cuenta2);

        banco.setNombre("Banco del Estado");
        banco.transferir(cuenta2, cuenta1, new BigDecimal(500));
        /*El siguiente con AssertAll sin llaves*/
        assertAll(() -> assertEquals("1000.8989", cuenta2.getSaldo().toPlainString(),()->"el valor del saldo de la cuenta2 no es el esperado."),
                  () -> assertEquals("3000", cuenta1.getSaldo().toPlainString(),()->"el valor del saldo de la cuenta1 no es el esperado."),
                  () -> assertEquals(2, banco.getCuentas().size(),()->"el banco no tiene las cuentas esperadas"),
                  () -> assertEquals("Banco del Estado", cuenta1.getBanco().getNombre()),
                  () -> assertEquals("Andres", banco.getCuentas().stream()
                          .filter(c -> c.getPersona().equals("Andres"))
                          .findFirst().get().getPersona()),
                  () -> assertTrue(banco.getCuentas().stream()
                          .filter(c -> c.getPersona().equals("Andres"))
                          .findFirst().isPresent()),
                  () -> assertTrue(banco.getCuentas().stream()
                          .anyMatch(c -> c.getPersona().equals("Jhon Doe")))
        );
        /*El siguiente con AssertAll con llaves {}*/
        /*
        assertAll(() ->
                {
                    assertEquals("1000.8989", cuenta2.getSaldo().toPlainString());

                },
                () -> {
                    assertEquals("3000", cuenta1.getSaldo().toPlainString());
                },
                () -> {
                    assertEquals(2, banco.getCuentas().size());
                },
                () -> {
                    assertEquals("Banco del Estado", cuenta1.getBanco().getNombre());
                },
                () -> {
                    assertEquals("Andres", banco.getCuentas().stream()
                            .filter(c -> c.getPersona().equals("Andres"))
                            .findFirst().get().getPersona());
                },
                () -> {
                    assertTrue(banco.getCuentas().stream()
                            .filter(c -> c.getPersona().equals("Andres"))
                            .findFirst().isPresent());
                },
                () -> {
                    assertTrue(banco.getCuentas().stream()
                            .anyMatch(c -> c.getPersona().equals("Jhon Doe")));
                });
        */

        /*El siguiente sin AssertAll*/
        /*
        assertEquals("1000.8989",cuenta2.getSaldo().toPlainString());
        assertEquals("3000",cuenta1.getSaldo().toPlainString());

        assertEquals(2,banco.getCuentas().size());
        assertEquals("Banco del Estado",cuenta1.getBanco().getNombre());
        assertEquals("Andres",banco.getCuentas().stream()
                    .filter(c -> c.getPersona().equals("Andres"))
                    .findFirst().get().getPersona());

        //Los siguientes son dos stream quie hacen lo mismo con diferente codificacion
        assertTrue(banco.getCuentas().stream()
                .filter(c -> c.getPersona().equals("Andres"))
                .findFirst().isPresent());

        assertTrue(banco.getCuentas().stream()
                .anyMatch(c -> c.getPersona().equals("Jhon Doe")));
         */
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testSoloWindows(){

    }

    @Test
    @EnabledOnOs({OS.LINUX,OS.MAC})
    void testSoloLinuxMac(){

    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void soloJdk8(){

    }

    @Test
    @EnabledOnJre(JRE.JAVA_15)
    void soloJdk15(){

    }

    @Test
    @DisabledOnJre(JRE.JAVA_15)
    void testNoJDK15(){

    }

    @Test
    void imprimirSystemProperties(){
        Properties properties = System.getProperties();
        properties.forEach( (k,v)-> System.out.println(k + ":" + v) );
    }

    @Test
    @EnabledIfSystemProperty(named="java.version", matches = ".*15.*")
    void testJavaVersion(){

    }

    @Test
    @DisabledIfSystemProperty(named="os.arch", matches=".*32.*")
    void testSolo64(){

    }

    @Test
    @EnabledIfSystemProperty(named="os.arch",matches=".*32.*")
    void testNo64(){

    }

    @Test
    @EnabledIfSystemProperty(named="user.name",matches="ldavila")
    void testUsername(){

    }

    @Test
    @EnabledIfSystemProperty(named="ENV",matches = "dev")
    void testDev(){

    }

    @Test
    void imprimirVariablesAmbiente(){
        Map<String,String> getenv = System.getenv();
        getenv.forEach( (k,v)-> System.out.println(k + " = " + v) );
    }

    @Test
    //@Disabled
    @EnabledIfEnvironmentVariable(named="JAVA_HOME",matches = ".*jdk-15.0.1.*")
    void testJavaHome(){

    }

    @Test
    @EnabledIfEnvironmentVariable(named="NUMBER_OF_PROCESSORS",matches = "8")
    void testProcesadores(){

    }

    @Test
    @EnabledIfEnvironmentVariable(named="ENVIRONMENT",matches = "dev")
    void testEnv(){

    }

    @Test
    @DisabledIfEnvironmentVariable(named="ENVIRONMENT",matches = "prod")
    void testEnvProdDisabled(){

    }

    @Test
    //@DisplayName("Probando el saldo de la cuenta, que no sea null, mayor que cero, valor esperado.")
    void testSaldoCuentaDev() {
        System.out.println("ENV: " + System.getProperty("ENV"));
        boolean esDev = "dev".equals(System.getProperty("ENV"));
        assumeTrue(esDev);
        //Cuenta cuenta = new Cuenta("Lennin", new BigDecimal("1000.12345"));
        //this.cuenta = new Cuenta("Lennin", new BigDecimal("1000.12345")); //lo llevamos como atributo de clase para probar el @BeforeEach
        assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }


}
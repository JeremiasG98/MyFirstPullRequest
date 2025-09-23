
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class PersonaTest {

    @Test
    void testCreacionPersonaValidaConFactory() throws PersonaException {
        // Escenario: Probar la creación de una persona con datos correctos
        Persona persona = Persona.crearPersona("Ana", 1.70, 70.5, "1995-05-15");

        assertNotNull(persona, "La persona no debería ser nula.");
        assertEquals("Ana", persona.getNombre(), "El nombre no es el esperado.");
        assertEquals(1.70, persona.getAltura(), 0.01, "La altura no es la esperada.");
        assertEquals(70.5, persona.getPeso(), 0.01, "El peso no es el esperado.");
        assertEquals(LocalDate.of(1995, 5, 15), persona.getFechaNacimiento(), "La fecha de nacimiento no es la esperada.");
        // Asumimos que la edad de una persona nacida en 1995 en 2025 es 30.
        // La edad es calculada dinámicamente, por lo que este test es un poco menos fiable a largo plazo.
        // Se podría mejorar utilizando un mock de la fecha actual, pero para fines de aprendizaje esto es suficiente.
        assertEquals(30, persona.getEdad(), "La edad no es la esperada.");
    }

    @Test
    void testCreacionConNombreNuloLanzaExcepcion() {
        // Escenario: Probar que crear una persona con nombre nulo lanza una excepción
        assertThrows(NombreNuloException.class,
                () -> Persona.crearPersona(null, 1.70, 70.5, "1995-05-15"),
                "Debería lanzar una excepción para nombre nulo.");
    }

    @Test
    void testCreacionConAlturaInvalidaLanzaExcepcion() {
        // Escenario: Probar que una altura negativa o cero lanza una excepción
        assertThrows(AlturaNegativaException.class,
                () -> Persona.crearPersona("Pedro", -1.70, 70.5, "1995-05-15"),
                "Debería lanzar una excepción para altura negativa.");
        assertThrows(AlturaNegativaException.class,
                () -> Persona.crearPersona("Pedro", 0, 70.5, "1995-05-15"),
                "Debería lanzar una excepción para altura cero.");
    }

    @Test
    void testCreacionConPesoInvalidoLanzaExcepcion() {
        // Escenario: Probar que un peso negativo o cero lanza una excepción
        assertThrows(PesoNegativoException.class,
                () -> Persona.crearPersona("Juan", 1.70, -70.5, "1995-05-15"),
                "Debería lanzar una excepción para peso negativo.");
        assertThrows(PesoNegativoException.class,
                () -> Persona.crearPersona("Juan", 1.70, 0, "1995-05-15"),
                "Debería lanzar una excepción para peso cero.");
    }

    @Test
    void testCreacionConFechaInvalidaLanzaExcepcion() {
        // Escenario: Probar que un formato de fecha incorrecto lanza una excepción
        assertThrows(FormatoFechaInvalidoException.class,
                () -> Persona.crearPersona("Luis", 1.70, 70.5, "15-05-1995"),
                "Debería lanzar una excepción para formato de fecha inválido.");
    }
}
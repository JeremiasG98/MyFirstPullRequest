
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class Persona {

    private String nombre;
    private double altura;
    private double peso;
    private LocalDate fechaNacimiento;

    // El constructor sigue siendo privado
    private Persona(String nombre, double altura, double peso, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.altura = altura;
        this.peso = peso;
        this.fechaNacimiento = fechaNacimiento;
    }

    // AHORA EL FACTORY METHOD ES PARTE DE LA CLASE PERSONA
    public static Persona crearPersona(String nombre, double altura, double peso, String fechaNacimientoStr) throws PersonaException
    {
        // Validar el nombre
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NombreNuloException("El nombre no puede ser nulo o vacío.");
        }

        // Validar altura y peso
        if (altura <= 0) {
            throw new AlturaNegativaException("La altura debe ser un valor positivo.");
        }
        if (peso <= 0) {
            throw new PesoNegativoException("El peso debe ser un valor positivo.");
        }

        // Validar la fecha de nacimiento
        LocalDate fechaNacimiento;
        try {
            fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
        } catch (DateTimeParseException e) {
            throw new FormatoFechaInvalidoException("Formato de fecha de nacimiento inválido. Use AAAA-MM-DD.");
        }

        return new Persona(nombre, altura, peso, fechaNacimiento);
    }

    public String getNombre() {
        return nombre;
    }

    public double getAltura() {
        return altura;
    }

    public double getPeso() {
        return peso;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getEdad() {
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }
}
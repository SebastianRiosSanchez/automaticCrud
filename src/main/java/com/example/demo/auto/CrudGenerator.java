package com.example.demo.auto;

import com.example.demo.infrastructure.entity.Data;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.antlr.v4.runtime.misc.Utils.writeFile;

@Component
public class CrudGenerator {

    // Implementación de los métodos
    public static void createdCrud(String modelClassName) throws ClassNotFoundException, IOException {

        // Se extrae el nombre de la clase
        Class<?> modelClass = Class.forName(modelClassName);

        // Se extrae el nombre del paquete
        String packageName = modelClass.getPackage().getName();

        //Obtener el nombre de la clase modelo
        String modelSimpleName = modelClass.getSimpleName();

        // Crear los directorios si no existen, recibe el nombre del paquete por parámetro
//        createDirectories(packageName);
        //Generar el archivo repositorio
        generateRepository(packageName, modelSimpleName);

        //Generar la interfaz para la CLASE DTO
        generateInterfaceDto(packageName, modelSimpleName);

        //Generar la clase que implementa la interfaz DTO
        generateDto(packageName, modelSimpleName);

        //Método para crear la interfaz de Service
        generateInterfaceService(packageName, modelSimpleName);

        //Método para crear la clase en service
        //generateService(packageName, modelSimpleName);

//        //Generar el archivo controller
        //generateController(packageName, modelSimpleName);

        System.out.println("Nombre del paquete: " + packageName);
        System.out.println("Nombre de la clase: " + modelSimpleName);

    }

    //----------------------------------------------------------------------------------------------------------------------
    //Método para crear los paquetes/carpetas
    private static void createDirectories(String packageName) {
        new File("src/main/java/" + packageName.replace(".", "/") + "/repository").mkdirs();
        new File("src/main/java/" + packageName.replace(".", "/") + "/service").mkdirs();
        new File("src/main/java/" + packageName.replace(".", "/") + "/dto").mkdirs();
        new File("src/main/java/" + packageName.replace(".", "/") + "/controller").mkdirs();
        //com.example.demo.entity.Data
    }

    //Método para generar el archivo de repositorio
    private static void generateRepository(String packageName, String modelSimpleName) throws ClassNotFoundException, IOException {

        Data data = new Data();
        String dataType = getDataType(data);

        String packageNameUpdate = "com.example.demo.infrastructure";
        String repositoryContent = "package " + packageNameUpdate + ".repository;\n\n" +
                "import " + packageName + "." + modelSimpleName + ";\n" +
                "import org.springframework.stereotype.Repository;\n\n" +
                "import org.springframework.data.jpa.repository.JpaRepository;\n" +
                "@Repository\n" +
                "public interface " + modelSimpleName + "Repository extends JpaRepository<" + modelSimpleName + "," + dataType + "> {\n" + "}\n";

        writeFile(packageNameUpdate, "repository", modelSimpleName + "Repository.java", repositoryContent);
    }

    //Método para generar la Interfaz que va a implementar la CLASE DTO
    private static void generateInterfaceDto(String packageName, String modelSimpleName) throws ClassNotFoundException, IOException {
        String packageNameUpdate = "com.example.demo.infrastructure";
        String interfaceDtoContent = "package " + packageNameUpdate + ".dto;\n\n" + "import " + packageName + "." + modelSimpleName + ";\n" + "import java.util.List;\n" + "import java.util.Optional;\n\n" + "public interface " + " i" + modelSimpleName + "Dto\n" + " {\n\n" + "     List<" + modelSimpleName + "> findAll();\n" + "     Optional<" + modelSimpleName + "> findById(Long id);\n" + "    Optional<" + modelSimpleName + "> save(" + modelSimpleName + " " + camelCase(modelSimpleName) + ");\n" + "    public void deleteById(Long id);\n\n" + "}\n";

        writeFile(packageNameUpdate.replace('.', '/'), "dto", "i" + modelSimpleName + "Dto.java", interfaceDtoContent);
    }

    //Método para dar formato a los nombres
    private static String camelCase(String str) {
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    //Método para escribir un archivo
    private static void writeFile(String packageName, String subPackage, String fileName, String content) throws IOException {
        String directoryPath = "src/main/java/" + packageName.replace(".", "/") + "/" + subPackage;
        File file = new File(directoryPath, fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(content);
        }
    }

    // Método para generar la clase DTO que va a implementar la interfaz DTO
    private static void generateDto(String packageName, String modelSimpleName) throws ClassNotFoundException, IOException {
        String packageNameUpdate = "com.example.demo.infrastructure";
        String dtoContent = "package " + packageNameUpdate + ".dto;\n\n" + "import org.springframework.stereotype.Component;\n\n" + "@Component\n" + "public class " + modelSimpleName + "Dto {\n" +

                "}\n";
        writeFile(packageNameUpdate.replace('.', '/'), "dto", modelSimpleName + "Dto.java", dtoContent);
    }


    //Método para generar la interfaz de servicio
    public static void generateInterfaceService(String packageName, String modelSimpleName) throws ClassNotFoundException, IOException {
        String packageNameUpdate = "com.example.demo.infrastructure";
        String interfaceServiceContent = "package " + packageNameUpdate + ".service;" + "\n\n" + "import " + packageNameUpdate + ".entity." + modelSimpleName + ";\n\n" + //***//
                "public interface i" + modelSimpleName + "Service" + "{\n\n" + "public " + modelSimpleName + " save(" + modelSimpleName + " " + camelCase(modelSimpleName) + ");\n" +
                //"Repository.save(" + camelCase(modelSimpleName) + ");\n" +
                //"    }\n\n" +
                //"public List<" + modelSimpleName + "> findAll();\n" +
                //"public Optional<" + modelSimpleName + "> findById(Long id);" +
                //"Repository.findById(id);" +
                "}\n";

        writeFile(packageNameUpdate.replace('.', '/'), "service", "i" + modelSimpleName + "Service.java", interfaceServiceContent);

    }


    //Método para generar el archivo de service
    private static void generateService(String packageName, String modelSimpleName) throws IOException {

        String packageNameUpdate = "com.example.demo.infrastructure";

        String serviceContent = "package " + packageNameUpdate + ".service;\n\n" + "import " + packageNameUpdate + ".entity" + "." + modelSimpleName + ";\n" + "import " + packageNameUpdate + ".repository." + modelSimpleName + "Repository;\n" + "import org.springframework.beans.factory.annotation.Autowired;\n" + "import org.springframework.stereotype.Service;\n\n" + "import java.util.List;\n" + "import java.util.Optional;\n\n" + "@Service\n" + "public class " + modelSimpleName + "Service {\n\n" + "    private final " + modelSimpleName + "Repository " + camelCase(modelSimpleName) + "Repository;\n\n" + "    @Autowired\n" + "    public " + modelSimpleName + "Service(" + modelSimpleName + "Repository " + camelCase(modelSimpleName) + "Repository) {\n" + "        this." + camelCase(modelSimpleName) + "Repository = " + camelCase(modelSimpleName) + "Repository;\n" + "    }\n\n" + "    " +
                //"public List<" + modelSimpleName + "> findAll() {\n" + "        return " + camelCase(modelSimpleName) + "Repository.findAll();\n" + "    }\n\n" + "    " +
                //"public Optional<" + modelSimpleName + "> findById(Long id) {\n" + "        return " + camelCase(modelSimpleName) +
//                "Repository.findById(id);\n" + "    }\n\n" + "    " +
                //"public " + modelSimpleName + " save(" + modelSimpleName + " " + camelCase(modelSimpleName) + ") {\n" + "        return " + camelCase(modelSimpleName) + "Repository.save(" + camelCase(modelSimpleName) + ");\n" + "    }\n\n" +
//                "    public void deleteById(Long id) {\n" + "        " + camelCase(modelSimpleName) + "Repository.deleteById(id);\n" + "    }\n"

                "}\n";


        writeFile(packageNameUpdate, "service", modelSimpleName + "Service.java", serviceContent);
    }


    // Método para conocer el tipo de dato del identificador único de una entidad.
    private static String getDataType(Object entity) {
        try {
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().contains("id")) {
                    Class<?> fieldType = field.getType();
                    System.out.println("El tipo de dato del campo '" + field.getName() + "' es: " + fieldType.getSimpleName());
                    return fieldType.getSimpleName();
                }
            }
            System.err.println("No se encontró ningún campo con las letras 'id'.");
            return null;
        } catch (SecurityException e) {
            System.err.println("Error al acceder a los campos de la clase.");
            return null;
        }
    }


    //----------------------------------------------------------------------------------------------------------------------


    //Método para crear el archivo de contolller
    private static void generateController(String packageName, String modelSimpleName) throws IOException {
        String packageNameUpdate = "com.example.demo.infrastructure";
        String controllerContent = "package " + packageNameUpdate + ".controller;\n\n" + "import " + packageNameUpdate + "." + modelSimpleName + ";\n" + "import " + packageNameUpdate + ".services." + modelSimpleName + "Service;\n" + "import org.springframework.beans.factory.annotation.Autowired;\n" + "import org.springframework.http.ResponseEntity;\n" + "import org.springframework.web.bind.annotation.*;\n\n" + "import java.util.List;\n" + "import java.util.Optional;\n\n" + "@RestController\n" + "@RequestMapping(\"/api/" + modelSimpleName.toLowerCase() + "\")\n" + "public class " + modelSimpleName + "Controller {\n\n" + "    private final " + modelSimpleName + "Service " + camelCase(modelSimpleName) + "Service;\n\n" + "    @Autowired\n" + "    public " + modelSimpleName + "Controller(" + modelSimpleName + "Service " + camelCase(modelSimpleName) + "Service) {\n" + "        this." + camelCase(modelSimpleName) + "Service = " + camelCase(modelSimpleName) + "Service;\n" + "    }\n\n" + "    @GetMapping\n" + "    public List<" + modelSimpleName + "> getAll" + modelSimpleName + "() {\n" + "        return " + camelCase(modelSimpleName) + "Service.findAll();\n" + "    }\n\n" + "    @GetMapping(\"/{id}\")\n" + "    public ResponseEntity<" + modelSimpleName + "> get" + modelSimpleName + "ById(@PathVariable Long id) {\n" + "        Optional<" + modelSimpleName + "> " + camelCase(modelSimpleName) + " = " + camelCase(modelSimpleName) + "Service.findById(id);\n" + "        if (" + camelCase(modelSimpleName) + ".isPresent()) {\n" + "            return ResponseEntity.ok(" + camelCase(modelSimpleName) + ".get());\n" + "        } else {\n" + "            return ResponseEntity.notFound().build();\n" + "        }\n" + "    }\n\n" + "    @PostMapping\n" + "    public " + modelSimpleName + " create" + modelSimpleName + "(@RequestBody " + modelSimpleName + " " + camelCase(modelSimpleName) + ") {\n" + "        return " + camelCase(modelSimpleName) + "Service.save(" + camelCase(modelSimpleName) + ");\n" + "    }\n\n" + "    @PutMapping(\"/{id}\")\n" + "    public ResponseEntity<" + modelSimpleName + "> update" + modelSimpleName + "(@PathVariable Long id, @RequestBody " + modelSimpleName + " " + camelCase(modelSimpleName) + "Details) {\n" + "        Optional<" + modelSimpleName + "> " + camelCase(modelSimpleName) + " = " + camelCase(modelSimpleName) + "Service.findById(id);\n" + "        if (" + camelCase(modelSimpleName) + ".isPresent()) {\n" + "            " + modelSimpleName + " updated" + modelSimpleName + " = " + camelCase(modelSimpleName) + ".get();\n" + "            updated" + modelSimpleName + ".setTestName(" + camelCase(modelSimpleName) + "Details.getTestName());\n" + "            return ResponseEntity.ok(" + camelCase(modelSimpleName) + "Service.save(updated" + modelSimpleName + "));\n" + "        } else {\n" + "            return ResponseEntity.notFound().build();\n" + "        }\n" + "    }\n\n" + "    @DeleteMapping(\"/{id}\")\n" + "    public ResponseEntity<Void> delete" + modelSimpleName + "(@PathVariable Long id) {\n" + "        Optional<" + modelSimpleName + "> " + camelCase(modelSimpleName) + " = " + camelCase(modelSimpleName) + "Service.findById(id);\n" + "        if (" + camelCase(modelSimpleName) + ".isPresent()) {\n" + "            " + camelCase(modelSimpleName) + "Service.deleteById(id);\n" + "            return ResponseEntity.noContent().build();\n" + "        } else {\n" + "            return ResponseEntity.notFound().build();\n" + "        }\n" + "    }\n" + "}\n";

        writeFile(packageNameUpdate, "controller", modelSimpleName + "Controller.java", controllerContent);
    }


    // "Método Principal"
    public static void main(String[] args) {
        try {
            createdCrud("com.example.demo.infrastructure.entity.Data");

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

}

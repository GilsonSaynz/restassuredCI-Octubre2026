Feature: Login
    Scenario: Como usuario quiero ingresar un email y password para iniciar sesión
      Given que tengo acceso a instagram
      When me registro usando
      | nombre    | apellidos  | telefono | direccion | dni |
      | Carlos | perez   | 23463    | peru      | 48559734 |
      Then muestra la pagina principal

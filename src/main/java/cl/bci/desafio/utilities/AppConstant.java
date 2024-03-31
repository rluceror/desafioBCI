package cl.bci.desafio.utilities;

public class AppConstant {

  // region constants

  // Validations Errors
  public static final String SESSION_EXCEPTION_MESSAGE = "token o sesion expirada.";
  public static final String ACCESS_DENIED_EXCEPTION_MESSAGE = "Access denegado";
  public static final String USER_DOES_NOT_EXIST_EXCEPTION_MESSAGE = "Usuario no existe.";
  public static final String USERS_DOES_NOT_EXIST_EXCEPTION_MESSAGE = "Usuario no existe..";
  public static final String CONSTRAINT_EMAIL_VIOLATION_EXCEPTION_MESSAGE = "Email existe";
  public static final String INTERNAL_SERVER_ERROR_EXCEPTION_MESSAGE =
      "En estos momentos no responde nuestro servicio";
  public static final String EMAIL_FORMAT_ERROR_MESSAGE = "Ingrese de forma correcta el correo por ejemplo : email@email.com";
  public static final String CREDENTIALS_FORMAT_ERROR_MESSAGE =
      "Contraseña de formato no válido. El patrón para crear la contraseña debe ser primero una letra mayúscula, luego letras minúsculas y los últimos dos números del cero al nueve. Ejemplo 'Contraseña22'.";

  // Null Field Error
  public static final String EMAIL_NULL_ERROR_MESSAGE = "correo electrónico no puede ser nulo.";
  public static final String NAME_NULL_ERROR_MESSAGE = "nombre no puede ser nulo.";
  public static final String CREDENTIALS_NULL_ERROR_MESSAGE = "contraseña no puede ser nulo.";
  public static final String PHONES_NULL_ERROR_MESSAGE = "telefono no puede ser nulo.";
  public static final String NUMBER_NULL_ERROR_MESSAGE = "numero no puede ser nulo.";
  public static final String CITYWIDE_NULL_ERROR_MESSAGE = "codigo_ciudad no puede ser nulo.";
  public static final String COUNTRYSIDE_NULL_ERROR_MESSAGE = "codigo_pais no puede ser nulo.";

  // Empty Field Error
  public static final String EMAIL_EMPTY_ERROR_MESSAGE = "correo electronico es requerido.";
  public static final String NAME_EMPTY_ERROR_MESSAGE = "nombre es requerido.";
  public static final String CREDENTIALS_EMPTY_ERROR_MESSAGE = "contraseña es requerido.";
  public static final String PHONES_EMPTY_ERROR_MESSAGE = "telefono es requerido.";
  public static final String NUMBER_EMPTY_ERROR_MESSAGE = "numero es requerido.";
  public static final String CITYWIDE_EMPTY_ERROR_MESSAGE = "codigo_ciudad es requerido.";
  public static final String COUNTRYSIDE_EMPTY_ERROR_MESSAGE = "codigo_pais es requerido.";

  // Patterns
  public static final String PATTERN_CREDENTIALS = "^([A-Z]{1})([a-z].*)([0-9]{2})";

  // Url Apis
  public static final String API_REST_UNPROTECTED_DOSIGNIN = "/v1/desafioBci/doSignIn";
  public static final String API_REST_PROTECTED_GETUSERS = "/v1/desafioBci/getUsers";
  public static final String API_REST_PROTECTED_FIND_USER_BY_EMAIL = "/v1/desafioBci/findUserByEmail";
  public static final String API_REST_PROTECTED_DOSIGNOUT = "/v1/desafioBci/doSignOut";

  // Roles
  public static final String ROL_ADMIN = "ROLE_ADMIN";
  public static final String ROL_USER = "ROLE_USER";
  public static final String HAS_ROL_USER = "USER";
  public static final String HAS_ROL_ADMIN = "ADMIN";

  // Others
  public static final long EXPIRED_TIME_TOKEN_SESSION = 300000;
  public static final String NAME_TOKEN_SESSION = "Token";
  public static final String FORMAT_MESSAGE_JWT = "{\"message\": \"%s\"}";
  public static final String TYPE_MESSAGE_JWT = "application/json";
  public static final String CHARACTER_ENCODING_MESSAGE_JWT = "UTF-8";
  public static final String DATE_PATTERN = "dd-MM-yyyy HH:mm:ss";
  public static final String SUCCESS_LOGOUT_MESSAGE = "Cierre de sesión exitoso, que tengas un gran día.";

  // endregion

  public AppConstant() {}
}

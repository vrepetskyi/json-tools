# JSON tools

## SE-2023 team project

---

> Application for programmers who need to reformat or filter data structures saved in JSON format and to compare the structures with each other. JSON tools application allows you to both minify the unminified JSON representation, as well as the reverse operation (with any blanks and new lines added). The application will be available via GUI, as well as as a remote API, so it can be integrated with existing tools.


**_The javadoc and .jar file are available [online](https://vrepetskyi.github.io/json-tools/)._**

## API Documentation

### JsonString

Checks if given input is in correct JSON format. 

**Methods:**

- `isValid(parameters)`: Checks whether the provided string is a valid JSON string.
- `setValue(parameters)`: Sets the value of the JsonString object to the provided string. This method validates the input value to ensure it is a valid JSON string.
- `getValue(parameters)`: Returns the value of the JsonString object as a formatted JSON string. The JsonFormatParams parameter is optional and can be used to specify formatting options.

**Fields:**

- `value`: Represents valid JSON string that can be used for further formattting. 

### JsonFormatter

It performs formatting operations such as minification, prettification, and filtering on the JSON string.

**Methods:**

- `format(String source, JsonFormatParams params)`: Formats the provided JSON string using the specified formatting parameters.

**Fields:**

---

### JsonFormatParams

The objective of the JsonFormatParams class is to provide a base class for defining parameters used for formatting JSON strings. It encapsulates the formatting options and provides methods to access these options.

**Methods:**

Only getters. 

**Fields:**

In this class, fields seems to be more important. 
- `styleMode`:  Represents the formatting style mode for JSON strings that can be Minify or Prettify.
- `filterMode`:  Represents the filtering mode for JSON strings that can be Include or Exclude.
- `filterKeys`:  Represents the set of keys to be filtered in the JSON string.

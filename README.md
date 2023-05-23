# JSON tools

Software Engineering final team project implementing an _API_ and _JavaFX_ interfaces

> Application for programmers who need to reformat or filter data structures saved in JSON format and to compare the structures with each other. JSON tools application allows you to both minify the unminified JSON representation, as well as the reverse operation (with any blanks and new lines added). The application will be available via GUI, as well as as a remote API, so it can be integrated with existing tools.

**_Javadoc and the compiled package are available [online](https://vrepetskyi.github.io/json-tools/)._**

## Usage

<table>
    <tr>
        <td><code>mvn install</code></td>
        <td>install <em>dependencies</em></td>
    </tr>
    <tr>
        <td><code>mvn javafx:run</code></td>
        <td><em>run</em> from source</td>
    </tr>
    <tr>
        <td><code>mvn package</code></td>
        <td>create a <em>package</em> (target/json-tools*.jar)</td>
    </tr>
    <tr>
        <td><code>mvn verify</code></td>
        <td>prepare everything for <em>deploy</em></td>
    </tr>
    <tr>
        <td><code>java -jar json-tools*.jar</code></td>
        <td><em>execute</em> the compiled package</td>
    </tr>
    <tr>
        <td><code>mvn clean</code></td>
        <td>in case something went wrong</td>
    </tr>
</table>

### API

<table>
    <tr>
        <td>Port</td>
        <td>8080</td>
    </tr>
    <tr>
        <td>Method</td>
        <td>POST</td>
    </tr>
</table>

#### /api/format

Request:

```json
{
    "source": { "object" },
    "params": {
        "filter": {
            "keys": ["array"],
            "exclude": true
        },
        "prettify": true
    }
}
```

Response:

<table>
    <tr>
        <td>Codes</td>
        <td>200, 400, 405, 500</td>
    </tr>
    <tr>
        <td>Body</td>
        <td>the resulting JSON or an error message</td>
    </tr>
</table>

#### /api/compare-lines

Request:

```json
{
    "s1": { "object" },
    "s2": { "object" }
}
```

Response:

<table>
    <tr>
        <td>Codes</td>
        <td>200, 405, 500</td>
    </tr>
    <tr>
        <td>Body</td>
        <td>numbers of different lines as a JSON array or an error message</td>
    </tr>
</table>

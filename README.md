## IgRok Net tools

package contains Email validator and Activation client

## Usage

as command line tool
``` powershell
java -cp ".\lib\org.igrok-net.hasher.jar;.\org.igrok-net.tools.jar" org.igrok_net.tools.Main -v -a <activation email> <license code from igrok-net.org> -e <email to validate>
```

as library

``` java
// activate
// if you use key directly
EmailValidator.activate(activationEmail, key, null);
//if you use license file
EmailValidator.activate(activationEmail, null, filePath);

// validate email
EmailValidator.validate(email);
```
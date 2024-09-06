## IgRok Net tools

package contains Email validator and Activation client

## Usage

To get product key please visit https://igrok-net.org/keys or http://igrok-net.org/keys if you are on windows xp.
To run this product you need java 8 or higher. We recommend to use java 8 if on windows xp or latest lts java if on newer systems.

### as command line tool
To run as command line tool you need to download both jar files and put them in classpath when running java command.
``` powershell
java -cp ".\lib\org.igrok-net.hasher.jar;.\org.igrok-net.tools.jar" org.igrok_net.tools.Main -v -a <activation email> <license code from igrok-net.org> -e <email to validate>
```
#### command line arguments
##### -v
display header and license information
##### -a
activate product with given email and key
use as follows: -a <activation email> <product key>
##### -e
email to validate
use as follows: -e <email to validate>

### as library

``` java
// activate
// if you use key directly
EmailValidator.activate(activationEmail, key, null);
//if you use license file
EmailValidator.activate(activationEmail, null, filePath);

// validate email
EmailValidator.validate(email);
```
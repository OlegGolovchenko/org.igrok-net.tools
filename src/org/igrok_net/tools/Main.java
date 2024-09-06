/*
 *  IgRok-NET tools
 *  Copyright (C) 2023  Oleg Golovchenko
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.igrok_net.tools;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

import org.igrok_net.tools.email.EmailValidator;
import org.igrok_net.tools.help.HelpInfo;

public class Main {

    private static HelpInfo helpInfo;

    public static void main(String[] args) {
        helpInfo = new HelpInfo();
        if (args.length == 0) {
            helpInfo.PrintHeader();
        } else {
            int i = 0;
            while (i < args.length) {
                String arg = args[i];
                if (arg.matches("-v")) {
                    helpInfo.PrintHeader();
                    try (InputStream input = Main.class.getResourceAsStream("/LICENSE.md")) {
                        try (Scanner s = new Scanner(input)) {
                            String output = "";
                            while (s.hasNext()) {
                                output += s.next();
                            }
                            Files.write(Paths.get(".", "LICENSE.md"), output.getBytes("Utf8"),
                                    StandardOpenOption.CREATE,
                                    StandardOpenOption.WRITE);
                        } catch (Exception ex) {
                            throw ex;
                        }
                    } catch (Exception ex) {
                        System.err.println(ex.getLocalizedMessage());
                    }
                }
                if (arg.matches("-a")) {
                    i++;
                    String email = args[i];
                    i++;
                    String key = args[i];
                    EmailValidator.activate(email, key, null);
                }
                if (arg.matches("-e")) {
                    i++;
                    arg = args[i];
                    try {
                        System.out.println(EmailValidator.ValidateEmail(arg));
                    } catch (Exception e) {
                        System.err.println(e.getLocalizedMessage());
                    }
                }
                i++;
            }
        }
    }
}

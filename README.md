# I'm not a robot
A multipurpose Discord bot that offers a variety of utility tools: **translator, dictionary, currency converter, view user info, and timer**.\
\
```Spoiler: It acts exactly like a robot.```

## 🧷 Invite link
https://discord.com/oauth2/authorize?client_id=978649404816916521&permissions=8&scope=bot

## 👋 Get Started
- **Prefix:** To use any of the bot's commands, type `e!` in front of the command.
- **Help:** To get the help menu, type `e!help`.
- **Language Help:** To get a list of supported languages for the translator feature, type `e!helpts`.
- **Currency Help:** To get a list of supported currencies for the currency converter feature, type `e!helpct`.
- **Time Help:** To get a list of supported time zones for the time feature, type `e!helptime`.

## 🤖 Commands Menu
- **Translator:** To translate a message to a specific language, type `e!ts targetLanguage message`. The bot will automatically detect the source language.
- **Dictionary:** To get the definition of an English word, type `e!d word`.
- **Currency Converter:** To convert one currency to another, type `e!e baseCurrency targetCurrency`.
- **Time:** To get the current time from a specific time zone, type `e!time timeZoneName` or `e!time timeZoneCode`.
- **Timer:** To set a timer, type `e!timer minutes`. The bot will send a message when the timer is up.
- **User Info:** To get information about a specific user, type `e!info @user`.
- **About:** To get information about the bot and its developer, type `e!about`.

## 📥 Installation
### 💻 Prerequisite
- Java Development Kit (JDK) 19 or later
- Maven 3.8.1 or later

### ⚙️ Setup
1. Clone the repository: Open a terminal and clone the IAmNotARobot repository using the following command:
   ```bash 
   git clone https://github.com/tansweeyang/IAmNotARobot.git
   ```
2. Install dependencies: Navigate to the cloned directory and run the following command to download all necessary libraries:
   ```bash
   mvn install
   ```
3. Follow the instructions from the websites to get your unique API keys:
   - Discord Jda API: https://discord.com/developers/docs/intro
   - Google Cloud Translation API: https://cloud.google.com/translate/docs/reference/rest
   - Currency Converter API: https://currencyapi.com/
4. Configure environment variables: Create a ```.env``` file in the root directory and add the following lines, replacing the placeholders with your actual values:
   ```env
   DISCORD_JDA_KEY=<Your Discord JDA Bot Token>
   GOOGLE_SCRIPT_TRANSLATOR_DEPLOYMENT_ID=<Google Apps Script Translator Deployment ID>
   CURRENCY_TRANSLATOR_API_KEY=<Currency Translator API Key>
   ```

5. Run the application: Execute the main class with the following command:
   ```bash
   java -cp target/IAmNotARobot-0.0.1-SNAPSHOT.jar com.eislyn.IAmNotARobot.app.IAmNotARobot
   ```

## 📤 Deployment
1. Prepare the JAR:
   - Run ```mvn package``` to build the project and generate a JAR file.
   - Rename the JAR file to ```bot.jar``` (required for Discloud).
   - Move the JAR file to the root directory.
2. Pack and Upload:
   - Zip the ```bot.jar``` file.
3. Join the Discloud Server:
   - https://discord.gg/discloud-584490943034425391.
4. Upload and Start:
   - In the Discloud server, type ```.upc``` (upload project) in the commands channel and follow the prompts to upload your zipped ```bot.zip``` file.
   - Once uploaded, type ```.start``` to launch the bot!

## 👥 Contribute
If you would like to contribute to this project, please fork the repository and submit a pull request.

## 🦻 Support
If you have any questions or issues, please feel free to contact me or open an issue in this repository.

## 📃 License
This project is licensed under the MIT License - see the LICENSE file for details.

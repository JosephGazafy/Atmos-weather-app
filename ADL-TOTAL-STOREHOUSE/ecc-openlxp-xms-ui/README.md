# OpenLXP - Experience Management Service UI (XMS UI)

The Experience Management Service is the human-facing application enabling an Experience Owner or Experience Manager to modify or augment a learning metadata record ingested by the Experience Index Service (XIS). XMS is a user interface facilitating modification and augmentation of records by learning experience owners and managers. This web application enables experience owners/managers to modify/augment experience metadata (i.e., the admin portal).

**Note: For this service to work properly you will need the XMS Backend component to accompany it.**

[![Version](https://img.shields.io/badge/version-prototype-yellow)](https://github.com/OpenLXP/openlxp-xms-ui)
[![yarn](https://img.shields.io/badge/yarn-1.22.1-blue)](https://yarnpkg.com/)
[![license](https://img.shields.io/badge/license-Apache_2.0-green)](https://github.com/OpenLXP/openlxp-xms-ui/blob/main/LICENSE)

[![react](https://img.shields.io/badge/react-17.0.1-61dafb)](https://reactjs.org/)
[![router](https://img.shields.io/badge/router-5.2.0-red)](https://reactrouter.com/web/guides/quick-start)
[![tailwind](https://img.shields.io/badge/tailwind-2.2.2-22d3ee)](https://redux-toolkit.js.org/)

## Prerequisites
### Install Docker & docker-compose
#### Windows & MacOS
- Download and install [Docker Desktop](https://www.docker.com/products/docker-desktop) (docker compose included)


#### Linux
You can download Docker Compose binaries from the
[release page](https://github.com/docker/compose/releases) on this repository.

Rename the relevant binary for your OS to `docker-compose` and copy it to `$HOME/.docker/cli-plugins`

Or copy it into one of these folders to install it system-wide:

* `/usr/local/lib/docker/cli-plugins` OR `/usr/local/libexec/docker/cli-plugins`
* `/usr/lib/docker/cli-plugins` OR `/usr/libexec/docker/cli-plugins`

(might require making the downloaded file executable with `chmod +x`)

## 1. Clone the repository
Clone the Github repository
```terminal
git clone git@github.com:OpenLXP/openlxp-xms-ui.git
```

## 2. Set up your environment variables
- Create a `.env` file in the root directory
- The following environment variables are required:

| Environment Variable            | Description                                                                      |
| ------------------------------- | -------------------------------------------------------------------------------- |
| NEXT_PUBLIC_XMS_BACKEND         | The endpoint for your XMS backend                                                |
| NEXT_PUBLIC_XIS_CATALOGS_API    | This is the root API endpoint used by the application to access XIS catalogs     |
| NEXT_PUBLIC_XIS_EXPERIENCES_API | This is the root API endpoint used by the application to access XIS experiences. |

**Note: These environment variables need to be set up at build time**

## 3. Deployment
1. Create the OpenLXP docker network. Open a terminal and run the following command in the root directory of the project.

    ```terminal
    docker network create openlxp
    ```

2. Run the command below to build your image for XMS UI

    ```terminal
    docker build -t xmsui .
    ```
  
3. Run the command below to deploy the image built in step 2

    ```terminal
    docker run -p 3000:3000 xmsui -d
    ```

  **Note: If deploying this at the same time as XMS UI, port mappings will need be changed to 3001:3000"

## Local development/testing
### 1. Install yarn

This project uses yarn as the package manager. If you already have yarn installed or are using a different package manager feel free to skip this step.

 - Start by installing yarn globally

    ```terminal
    npm install -g yarn
    ```
 
 - Verify yarn was installed

    ```terminal
    yarn -version
    ```

### 2: Install project dependencies

   - Installs all requirements for development
      
      ```terminal
      yarn install
      ```

### 3. Build the project

  - bundle your app into static files

      ```terminal
      yarn build
      ``` 

### 4. Run your project
  
  - Run the project in development mode

    ```terminal
    yarn start
    ```

## Testing

All of the components in the project are unit tested and are covered by the [Jest](https://jestjs.io/) testing framework. When testing components there are three key files to utilize:

1. `jest.setup.js`: This file is used to configure the testing environment including any mocks and setup functions for the code to work.
2. `mockSetUp.js`: This file is used to mock any functions that are reliant on external APIs or services.
3. `.test.js`: This file is used to test the components. Any file in the **tests** directory will be run by the testing framework as long as the child components are appended with `.test.js` or `.spec.js`.

### Our current threshold for testing coverage is:

- **Statement Coverage**: 80%
- **Branch Coverage**: 80%
- **Function Coverage**: 80%
- **Line Coverage**: 80%

### Command to run tests

 - Runs all the tests in the project with cached results

    ```terminal
    yarn test
    ```

- Run all the tests in the project without cached results. This produces a coverage report which can be viewed in the terminal or in the browser by opening the `/coverage/Icov-report/index.html` file in the project directory.

    ```terminal
    yarn coverage
    ```

## Documentation

### Frontend Stack Documentation

[Next.js Documentation can be found here](https://nextjs.org/docs)

[React-Query Documentation can be found here](https://react-query.tanstack.com/overview)

[TailwindCSS Documentation can be found here](https://tailwindcss.com/docs/installation)

[Axios Documentation can be found here](https://axios-http.com/docs/intro)

[HeadlessUi Documentation can be found here](https://headlessui.dev/)

### Dev Tools Documentation

[Eslint Documentation can be found here](https://eslint.org/docs/user-guide/configuring)

[Prettier Documentation can be found here](https://prettier.io/docs/en/install.html)

[Jest Documentation can be found here](https://jestjs.io/docs/en/getting-started)
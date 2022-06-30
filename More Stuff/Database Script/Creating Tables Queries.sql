DROP DATABASE IF EXISTS wolfsthetix;
CREATE DATABASE IF NOT EXISTS wolfsthetix;

DROP TABLE IF EXISTS general_information;
DROP TABLE IF EXISTS more_information;
DROP TABLE IF EXISTS positions;
DROP TABLE IF EXISTS personal_position;
DROP TABLE IF EXISTS feedback;
DROP TABLE IF EXISTS foods;
DROP TABLE IF EXISTS nutrients;
DROP TABLE IF EXISTS activity;
DROP TABLE IF EXISTS confirmation_token;

START TRANSACTION;

CREATE TABLE IF NOT EXISTS general_information (
    ID INT AUTO_INCREMENT,
    nickname VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email_address VARCHAR(50) UNIQUE NOT NULL,
    phone_number VARCHAR(10) UNIQUE NOT NULL,
    registration_date DATETIME DEFAULT NOW(),
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    date_of_birth DATE NOT NULL,
    PRIMARY KEY (ID),
    CHECK (LENGTH(nickname) >= 5
        AND email_address REGEXP "^[a-zA-Z0-9][a-zA-Z0-9.!#$%&'*+-/=?^_`{|}~]*?[a-zA-Z0-9._-]?@[a-zA-Z0-9][a-zA-Z0-9._-]*?[a-zA-Z0-9]?\\.[a-zA-Z]{2,63}$"
        AND phone_number REGEXP "^[0-9]{10}$")
);

CREATE TABLE IF NOT EXISTS more_information (
    secondary_ID INT AUTO_INCREMENT,
    gender ENUM('M', 'F') NOT NULL,
    height_cm DOUBLE NOT NULL,
    weight_kg DOUBLE NOT NULL,
    activity_level ENUM(
    'None or zero activity = Basal body calorie burnig rate (doing nothing)', 
    'Sedentary activity level = Little or no exercise', 
    'Light activity level = 1 - 2 times per week of training', 
    'Moderate activity level = 3 - 4 times per week of training', 
    'Intense activity level = 5 - 6 times per week of training', 
    'Super intense activity level = More than 6 times per week of training and other physical activities') NOT NULL,
    age INT NOT NULL,
    goal_kg DOUBLE NOT NULL,
    user_ID INT NOT NULL,
    PRIMARY KEY (secondary_ID),
    CONSTRAINT FKey_MI_GI FOREIGN KEY (user_ID) REFERENCES general_information (ID)
);

CREATE TABLE IF NOT EXISTS positions (
    positions_ID INT AUTO_INCREMENT,
    position ENUM('MEMBER', 'VIP', 'BANNED', 'FITNESS_INSTRUCTOR', 'MODERATOR', 'ADMIN'),
    PRIMARY KEY (positions_ID)
);
insert into positions (position) values ('MEMBER');
insert into positions (position) values ('VIP');
insert into positions (position) values ('BANNED');
insert into positions (position) values ('FITNESS_INSTRUCTOR');
insert into positions (position) values ('MODERATOR');
insert into positions (position) values ('ADMIN');

CREATE TABLE IF NOT EXISTS personal_position (
    person_ID INT,
    position_ID INT,
    PRIMARY KEY (person_ID , position_ID),
    CONSTRAINT FKey_PP_GI FOREIGN KEY (person_ID) REFERENCES general_information (ID),
    CONSTRAINT FKey_PP_positions FOREIGN KEY (position_ID) REFERENCES positions (positions_ID)
);

CREATE TABLE IF NOT EXISTS feedback (
    feedback_ID INT AUTO_INCREMENT,
    content TEXT NOT NULL,
    sent_by INT NOT NULL,
    PRIMARY KEY (feedback_ID),
    CONSTRAINT FKey_FB_GI FOREIGN KEY (sent_by) REFERENCES general_information (ID)
);

CREATE TABLE IF NOT EXISTS foods (
    foods_ID INT AUTO_INCREMENT,
    food_name VARCHAR(50) UNIQUE NOT NULL,
    food_type ENUM('MEATS', 'DAIRY_PRODUCTS_AND_EGGS', 'FISH', 'STEW_FOODS', 'VEGETABLES', 'FRUITS', 'NUTTS_AND_HEALTHY_FATS', 'HEALTHY_SWEETENERS'),
    PRIMARY KEY (foods_ID)
);

CREATE TABLE IF NOT EXISTS nutrients (
    nutrients_ID INT AUTO_INCREMENT,
    protein DOUBLE NOT NULL,
    carbohydrates DOUBLE NOT NULL,
    sugars DOUBLE,
    fiber DOUBLE,
    fats DOUBLE NOT NULL,
    saturated_fats INT,
    polyunsaturated_fats INT,
    monounsaturated_fats INT,
    trans_fats INT,
    cholesterol INT,
    vitamins VARCHAR(50),
    minerals VARCHAR(100),
    calories INT NOT NULL,
    food_ID INT NOT NULL,
    PRIMARY KEY (nutrients_ID),
    CONSTRAINT FKey_nutrients_foods FOREIGN KEY (food_ID) REFERENCES foods (foods_ID)
);

CREATE TABLE IF NOT EXISTS activity (
    activity_ID INT AUTO_INCREMENT,
    calories_consumed INT NOT NULL,
    calories_burned INT,
    steps_walked INT,
    daily_water_consumed_liters DOUBLE,
    activity_for_this_day DATETIME DEFAULT NOW(),
    user_activity_ID INT NOT NULL,
    PRIMARY KEY (activity_ID),
    CONSTRAINT FKey_activity_GI FOREIGN KEY (user_activity_ID) REFERENCES general_information (ID)
);

CREATE TABLE IF NOT EXISTS confirmation_token (
    confirmation_token_ID INT AUTO_INCREMENT,
    token VARCHAR(255),
    created_At DATETIME DEFAULT NOW(),
    expired_At DATETIME,
    confirmed_At DATETIME,
    PRIMARY KEY (confirmation_token_ID)
);

COMMIT;
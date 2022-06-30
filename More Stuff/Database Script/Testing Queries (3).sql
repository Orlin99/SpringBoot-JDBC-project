SELECT positions.position, nickname, email_address, first_name
FROM general_information
JOIN personal_position
ON general_information.ID = personal_position.person_ID
JOIN positions
ON personal_position.position_ID = positions.position
WHERE general_information.ID = 14;

SELECT * FROM general_information;

SELECT * FROM more_information;

SELECT * FROM activity;

SELECT * FROM foods
JOIN nutrients
ON foods.foods_ID = nutrients.food_ID;

SELECT * FROM feedback;
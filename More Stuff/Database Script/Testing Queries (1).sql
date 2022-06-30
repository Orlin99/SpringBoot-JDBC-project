SELECT foods.food_name, foods.food_type,
protein, carbohydrates, sugars, fiber, fats, saturated_fats, polyunsaturated_fats, 
monounsaturated_fats, trans_fats, cholesterol, vitamins, minerals, calories, food_ID
FROM nutrients
JOIN foods
ON nutrients.food_ID = foods.foods_ID
WHERE ? LIKE '%?%'
ORDER BY ? ASC
LIMIT ?;

SELECT foods.food_name, foods.food_type,
protein, carbohydrates, sugars, fiber, fats, saturated_fats, polyunsaturated_fats, 
monounsaturated_fats, trans_fats, cholesterol, vitamins, minerals, calories, food_ID
FROM nutrients
JOIN foods
ON nutrients.food_ID = foods.foods_ID
WHERE food_name = '?';



Insert into foods (food_name, food_type)
values ('chicken', 'MEATS');

insert into nutrients (protein,
    carbohydrates,
    sugars,
    fiber,
    fats,
    saturated_fats,
    polyunsaturated_fats ,
    monounsaturated_fats ,
    trans_fats ,
    cholesterol,
    vitamins ,
    minerals ,
    calories,
    food_ID) Values(50,1,1,1,1,1,1,1,1,1,'K','A',213,1);
    
    Insert into foods (food_name, food_type)
values ('pork', 'MEATS');

insert into nutrients (protein,
    carbohydrates,
    sugars,
    fiber,
    fats,
    saturated_fats,
    polyunsaturated_fats ,
    monounsaturated_fats ,
    trans_fats ,
    cholesterol,
    vitamins ,
    minerals ,
    calories,
    food_ID) Values(40,1,1,1,20,1,1,1,1,1,'K','A',213,2);

SELECT general_information.nickname, activity_ID, calories_consumed, calories_burned, steps_walked, daily_water_consumed_liters, user_activity_ID
                FROM activity
                JOIN general_information
                ON activity.user_activity_ID = general_information.ID
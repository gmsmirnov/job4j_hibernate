select car.name, body_id.name, engine_id.name, transmission_id.name
from ((car left join body on car.body_id = body_id.id)
left join engine on car.engine_id = engine_id.id)
left join transmission on car.transmission_id = transmission_id.id;

select body_id.name from car right join body on car.body_id = body_id.id where car.body_id is null;

select engine_id.name from car right join engine on car.engine_id = engine_id.id where car.engine_id is null;

select transmission_id.name from car right join transmission on car.transmission_id = transmission_id.id where car.transmission_id is null;
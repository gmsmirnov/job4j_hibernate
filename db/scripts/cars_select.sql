select car.name, body.name, engine.name, transmission.name 
from ((car left join body on car.body = body.id) 
left join engine on car.engine = engine.id)
left join transmission on car.transmission = transmission.id;

select body.name from car right join body on car.body = body.id where car.body is null;

select engine.name from car right join engine on car.engine = engine.id where car.engine is null;

select transmission.name from car right join transmission on car.transmission = transmission.id where car.transmission is null;
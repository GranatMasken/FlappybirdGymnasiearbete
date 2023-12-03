extends StaticBody2D
#Funktion som körs varje speluppdatering
func _physics_process(delta):
	#Rören flyttas åt vänster
	position += Vector2(-5*delta*60, 0)

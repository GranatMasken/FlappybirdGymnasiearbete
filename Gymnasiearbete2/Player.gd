extends CharacterBody2D
#Definerar konstanternan FLAP och GRAVITY
const FLAP = 500
const GRAVITY = 1000
#Sparar väggscenens data i variabeln Wall
var wall = preload("res://wallnode.tscn")
#Definerar poäng variabel
var score = 0
#Process funktion som körs varje speluppdatering, 
#delta är tiden sedan senaste uppdateringen
func _physics_process(delta):
	#Ökar hastigheten beroende på tyngdacceleration och delta
	velocity.y += GRAVITY * delta
	#Hoppar ifall FLAP sker
	if  Input.is_action_just_pressed("FLAP"):
		#Sätter hastigheten till -FLAP
		velocity.y = -FLAP
	#kör move_and_slide() funktionen
	move_and_slide()
	#Skriver nuvarnande poängen
	get_parent().get_parent().get_node("CanvasLayer/RichTextLabel").text = str(score)
#Definerar wall_reset funktionen
func wall_reset():
	#Definerar objektet wall_instance som är en ny instans av wall scenen
	var wall_instance = wall.instantiate()
	#Sätter ny position för wall_instance
	wall_instance.position = Vector2(1800, randi_range(-1000, -256))
	#Lägger till den nya instansen i spelet
	get_parent().call_deferred("add_child", wall_instance)
#Funktion som körs när en av väggarna träffar resettern
func _on_resetter_body_entered(body):
	if body.name == "Walls":
		#Tar bort den gamla väggen
		body.queue_free()
		#Kör wall_reset funktionen
		wall_reset()
#Funktion som körs om fågeln hamnar i poäng ytan
func _on_detect_area_entered(area):
	if area.name == "Area2D":
		#ökar score med 1
		score += 1
#Funktion som körs ifall fågeln träffar någon av väggarna
func _on_detect_body_entered(body):
	if body.name == "Walls":
		#Startar om spelet
		get_tree().reload_current_scene()

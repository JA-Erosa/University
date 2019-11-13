
% Empezamos el programa preguntando el nombre del paciente y luego prolog
% hará un match con las funciones declaradas abajo
% Al finalizar se describe la enfermedad que tiene el paciente, y en caso de 
% no figurar los síntomas en la base de datos, se le imprimirá al usuario
% que el sistema no es capaz de diagnosticar la enfermedad
go :-
    write('What is the patient''s name? '),
    read(Patient),
    hypothesis(Patient,Disease),
    write_list([Patient,' probably has ',Disease,'.']),nl.

go :-
    write('Sorry, I don''t seem to be able to diagnose the disease.'),nl.

%Síntomas (inicio de base de datos)
% Si tiene fiebre 
symptom(Patient,fever) :-
    write_list(['Does ',Patient,' have a fever (y/n) ?']),
    response(Reply),
    Reply='y'.

% Si tiene erupciones 
symptom(Patient,rash) :-
    write_list(['Does ',Patient,' have a rash (y/n) ?']),
    response(Reply),
    Reply='y'.

% Si tiene dolor de cabeza 
symptom(Patient,headache) :-
    write_list(['Does ',Patient,' have a headache (y/n) ?']),
    response(Reply),
    Reply='y'.

% Si tiene nariz con flujo 
symptom(Patient,runny_nose) :-
    write_list(['Does ',Patient,' have a runny_nose (y/n) ?']),
    response(Reply),
    Reply='y'.

% Si tiene conjuntivitis 
symptom(Patient,conjunctivitis) :-
    write_list(['Does ',Patient,' have a conjunctivitis (y/n) ?']),
    response(Reply),
    Reply='y'.

% Si tiene tos 
symptom(Patient,cough) :-
    write_list(['Does ',Patient,' have a cough (y/n) ?']),
    response(Reply),
    Reply='y'.

% Si tiene comezón 
symptom(Patient,body_ache) :-
    write_list(['Does ',Patient,' have a body_ache (y/n) ?']),
    response(Reply),
    Reply='y'.

% Si tiene escalofríos 
symptom(Patient,chills) :-
    write_list(['Does ',Patient,' have a chills (y/n) ?']),
    response(Reply),
    Reply='y'.

% Si tiene dolor de garganta 
symptom(Patient,sore_throat) :-
    write_list(['Does ',Patient,' have a sore_throat (y/n) ?']),
    response(Reply),
    Reply='y'.

% Si tiene estornudos 
symptom(Patient,sneezing) :-
    write_list(['Does ',Patient,' have a sneezing (y/n) ?']),
    response(Reply),
    Reply='y'.

% Si tiene las glándulas inflamadas 
symptom(Patient,swollen_glands) :-
    write_list(['Does ',Patient,' have a swollen_glands (y/n) ?']),
    response(Reply),
    Reply='y'.

% Hipótesis 
% En este apartado iremos preguntando (en orden) y si el paciente responde que no, se salta a la siguiente hipótesis. 
% Se introdujo al principio un dummy porque el programa salta esta primera pregunta (por alguna extraña razón) cuando el usuario da 
% enter

hypothesis(Patient,dummy) :-
	symptom(Patient,fever).

% Checa si el usuario tiene sarampión
hypothesis(Patient,measles) :-
	symptom(Patient,conjunctivitis),
	symptom(Patient,cough),
    symptom(Patient,fever),
    symptom(Patient,runny_nose),
    symptom(Patient,rash).

% Checa si el usuario tiene rubeola
hypothesis(Patient,german_measles) :-
    symptom(Patient,fever),
    symptom(Patient,headache),
    symptom(Patient,runny_nose),
    symptom(Patient,rash).

% Checa si el paciente tiene gripe
hypothesis(Patient,flu) :-
    symptom(Patient,chills),
    symptom(Patient,fever),
    symptom(Patient,headache),
    symptom(Patient,body_ache),
    symptom(Patient,conjunctivitis),
    symptom(Patient,sore_throat),
    symptom(Patient,runny_nose),
    symptom(Patient,cough).

% Checa si el paciente tiene un resfriado
hypothesis(Patient,common_cold) :-
    symptom(Patient,headache),
    symptom(Patient,sneezing),
    symptom(Patient,sore_throat),
    symptom(Patient,runny_nose),
    symptom(Patient,chills).

% Checa si el paciente tiene paperas
hypothesis(Patient,mumps) :-
	symptom(Patient,swollen_glands),
    symptom(Patient,fever).

% Checa si el paciente tiene varicela
hypothesis(Patient,chicken_pox) :-
    symptom(Patient,body_ache),
    symptom(Patient,fever),
    symptom(Patient,chills),
    symptom(Patient,rash).

% Declaramos una manera de escibir la lista, teniendo dos tipos de "Términos"
% para poder diferenciar el string normal de la variable 
write_list([]).
write_list([Term| Terms]) :-
    write(Term),
    write_list(Terms).

% Declaramos la función de respuesta para que lea el caracter ingresado
% (y o n)
% y luego que el output sea el caracter, mientras tanto el get_single_char nos
% permite no esperar a otra acción, sino que lee apenas el usuario ingrese un 
% caracter
response(Reply) :-
    get_single_char(Code),
    put_code(Code), nl,
    char_code(Reply, Code).

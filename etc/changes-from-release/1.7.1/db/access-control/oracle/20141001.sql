
UPDATE TB_APPS
SET CODE = 'GESTOR_AVISOS', 
    TITLE = 'Gestor de avisos',
    DESCRIPTION = 'Aplicativo encargado de gestionar los avisos que se emiten desde diferentes aplicaciones del organismo'
WHERE CODE = 'GESTOR_NOTIFICACIONES';

commit;
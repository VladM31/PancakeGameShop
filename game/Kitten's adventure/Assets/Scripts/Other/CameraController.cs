using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraController : MonoBehaviour
{
    public Transform target; // Ссылка на игрока
    public float minX; // Минимальная позиция камеры по оси X
    public float maxX; // Максимальная позиция камеры по оси X
    public float minY; // Минимальная позиция камеры по оси Y
    public float maxY; // Максимальная позиция камеры по оси Y

    private void LateUpdate()
    {
        // Проверяем, есть ли ссылка на игрока
        if (target == null)
            return;

        // Получаем текущую позицию игрока
        Vector3 targetPosition = target.position;

        // Ограничиваем позицию камеры в пределах заданных границ
        float clampedX = Mathf.Clamp(targetPosition.x, minX, maxX);
        float clampedY = Mathf.Clamp(targetPosition.y, minY, maxY);

        // Устанавливаем новую позицию камеры
        transform.position = new Vector3(clampedX, clampedY, transform.position.z);
    }
}

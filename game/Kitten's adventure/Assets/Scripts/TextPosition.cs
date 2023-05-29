using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TextPosition : MonoBehaviour
{
    public Transform target; // Ссылка на объект, к которому будет привязан текст
    private RectTransform rectTransform;
    public float yOffset = 150f; // Регулируйте этот параметр для настройки смещения


    private void Start()
    {
        rectTransform = GetComponent<RectTransform>();
    }

    private void Update()
    {
        if (target != null)
        {
            // Получаем позицию объекта в мировых координатах
            Vector3 worldPosition = target.position;

            // Преобразуем позицию объекта в экранные координаты
            Vector3 screenPosition = Camera.main.WorldToScreenPoint(worldPosition);

            // Добавляем смещение вверх к позиции текста
            screenPosition.y += yOffset;

            // Устанавливаем новую позицию текста
            rectTransform.position = screenPosition;
        }
    }
}

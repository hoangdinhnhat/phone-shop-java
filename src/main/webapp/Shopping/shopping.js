var sliderImages = document.querySelector('#body .slider .images')

const prevBtn = document.querySelector('#body .slider .prev')
prevBtn.onclick = () => {
    const curSlide = document.querySelector('#body .slider img:nth-child(1)')
    sliderImages.appendChild(curSlide)
}

const nextBtn = document.querySelector('#body .slider .next')
nextBtn.onclick = () => {
    const curSlide = document.querySelector('#body .slider img:nth-child(4)')
    sliderImages.prepend(curSlide)
}

setInterval(function () {
    const curSlide = document.querySelector('#body .slider img:nth-child(4)')
    sliderImages.prepend(curSlide)
}, 3000);

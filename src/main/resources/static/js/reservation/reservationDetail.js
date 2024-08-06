document.getElementById('more-button').addEventListener('click', function() {
    this.classList.toggle('active');
    if (this.classList.contains('active')) {
        this.style.backgroundColor = '#FA4A54';
        this.style.color = '#fff';
    } else {
        this.style.backgroundColor = '#fff';
        this.style.color = '#FA4A54';
    }
});

const images = [
    'https://via.placeholder.com/150?text=Image+1',
    'https://via.placeholder.com/150?text=Image+2',
    'https://via.placeholder.com/150?text=Image+3',
    'https://via.placeholder.com/150?text=Image+4',
    'https://via.placeholder.com/150?text=Image+5',
    'https://via.placeholder.com/150?text=Image+6',
    'https://via.placeholder.com/150?text=Image+7',
    'https://via.placeholder.com/150?text=Image+8'
];
let currentIndexes = [0, 1, 2, 3];

document.getElementById('arrow-button').addEventListener('click', function() {
    currentIndexes = currentIndexes.map(index => (index + 1) % images.length);
    currentIndexes.forEach((index, i) => {
        document.getElementById(`card-${i}`).querySelector('.img-placeholder').style.backgroundImage = `url(${images[index]})`;
    });
});
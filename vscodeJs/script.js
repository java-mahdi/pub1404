function loadScript(src, callback) {
  const script = document.createElement('script');
  script.src = src;

  // When the script loads successfully
  script.onload = function() {
    console.log('Script loaded:', src);
    if (callback) callback(null, script);
  };

  // If the script fails to load
  script.onerror = function() {
    console.error('Failed to load script:', src);
    if (callback) callback(new Error(`Failed to load ${src}`));
  };

  // Append script to <head> or <body>
  document.head.appendChild(script);
}

// Usage: Load jQuery dynamically
loadScript('https://code.jquery.com/jquery-3.7.1.min.js', function(err, script) {
  if (err) {
    console.error('Error:', err);
  } else {
    console.log('jQuery is now loaded and ready to use!');
    
    // Now you can safely use jQuery
    window.jQuery && jQuery('body').append('<p>jQuery loaded dynamically!</p>');
  }
});


import Cocoa

class ViewController: NSViewController {
    
    @IBOutlet weak var browseView: BrowseView!

    override func viewDidLoad() {
        super.viewDidLoad()
        setupBrowseView()
    }
    
    private func setupBrowseView() {
        if browseView == nil {
            let browseViewFrame = NSRect(x: 0, y: 0, width: view.bounds.width, height: view.bounds.height)
            browseView = BrowseView(frame: browseViewFrame)
            view.addSubview(browseView)
            
            browseView.translatesAutoresizingMaskIntoConstraints = false
            NSLayoutConstraint.activate([
                browseView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
                browseView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
                browseView.topAnchor.constraint(equalTo: view.topAnchor),
                browseView.bottomAnchor.constraint(equalTo: view.bottomAnchor)
            ])
        }
    }

    override var representedObject: Any? {
        didSet {
            // Update the view, if already loaded.
        }
    }

}
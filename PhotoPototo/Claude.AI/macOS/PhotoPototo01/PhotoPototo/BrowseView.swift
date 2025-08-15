import Cocoa
import Foundation

class BrowseView: NSView {
    
    private var scrollView: NSScrollView!
    private var tableView: NSTableView!
    private var directories: [URL] = []
    
    override init(frame frameRect: NSRect) {
        super.init(frame: frameRect)
        setupView()
        loadDirectories()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setupView()
        loadDirectories()
    }
    
    private func setupView() {
        // Create scroll view
        scrollView = NSScrollView(frame: bounds)
        scrollView.autoresizingMask = [.width, .height]
        scrollView.hasVerticalScroller = true
        scrollView.hasHorizontalScroller = true
        scrollView.autohidesScrollers = true
        addSubview(scrollView)
        
        // Create table view
        tableView = NSTableView()
        tableView.headerView = nil
        tableView.intercellSpacing = NSSize(width: 0, height: 0)
        tableView.usesAlternatingRowBackgroundColors = true
        tableView.columnAutoresizingStyle = .uniformColumnAutoresizingStyle
        
        // Create table column
        let column = NSTableColumn(identifier: NSUserInterfaceItemIdentifier("DirectoryColumn"))
        column.title = "Directories"
        column.minWidth = 200
        tableView.addTableColumn(column)
        
        // Set data source and delegate
        tableView.dataSource = self
        tableView.delegate = self
        
        scrollView.documentView = tableView
    }
    
    private func loadDirectories() {
        directories.removeAll()
        
        // Get all volumes (mounted drives)
        let fileManager = FileManager.default
        let volumeURLs = fileManager.mountedVolumeURLs(includingResourceValuesForKeys: nil, options: []) ?? []
        
        for volumeURL in volumeURLs {
            // Skip network volumes and other special volumes
            if volumeURL.path.starts(with: "/Volumes/") && volumeURL.path != "/Volumes" {
                continue
            }
            
            directories.append(volumeURL)
            
            // Add common directories
            addDirectoryIfExists(volumeURL.appendingPathComponent("Users"))
            addDirectoryIfExists(volumeURL.appendingPathComponent("Applications"))
            addDirectoryIfExists(volumeURL.appendingPathComponent("System"))
            addDirectoryIfExists(volumeURL.appendingPathComponent("Library"))
        }
        
        // Add user directories
        if let homeURL = fileManager.urls(for: .homeDirectory, in: .userDomainMask).first {
            directories.append(homeURL)
            addDirectoryIfExists(homeURL.appendingPathComponent("Desktop"))
            addDirectoryIfExists(homeURL.appendingPathComponent("Documents"))
            addDirectoryIfExists(homeURL.appendingPathComponent("Downloads"))
            addDirectoryIfExists(homeURL.appendingPathComponent("Pictures"))
            addDirectoryIfExists(homeURL.appendingPathComponent("Music"))
            addDirectoryIfExists(homeURL.appendingPathComponent("Movies"))
        }
        
        // Sort directories by path
        directories.sort { $0.path < $1.path }
        
        tableView.reloadData()
    }
    
    private func addDirectoryIfExists(_ url: URL) {
        let fileManager = FileManager.default
        var isDirectory: ObjCBool = false
        
        if fileManager.fileExists(atPath: url.path, isDirectory: &isDirectory) && isDirectory.boolValue {
            directories.append(url)
        }
    }
}

// MARK: - NSTableViewDataSource
extension BrowseView: NSTableViewDataSource {
    func numberOfRows(in tableView: NSTableView) -> Int {
        return directories.count
    }
}

// MARK: - NSTableViewDelegate
extension BrowseView: NSTableViewDelegate {
    func tableView(_ tableView: NSTableView, viewFor tableColumn: NSTableColumn?, row: Int) -> NSView? {
        let identifier = NSUserInterfaceItemIdentifier("DirectoryCell")
        
        var cellView = tableView.makeView(withIdentifier: identifier, owner: self) as? NSTableCellView
        
        if cellView == nil {
            cellView = NSTableCellView()
            cellView?.identifier = identifier
            
            let textField = NSTextField()
            textField.isBordered = false
            textField.backgroundColor = NSColor.clear
            textField.isEditable = false
            textField.isSelectable = false
            textField.cell?.lineBreakMode = .byTruncatingMiddle
            
            cellView?.addSubview(textField)
            cellView?.textField = textField
            
            textField.translatesAutoresizingMaskIntoConstraints = false
            NSLayoutConstraint.activate([
                textField.leadingAnchor.constraint(equalTo: cellView!.leadingAnchor, constant: 4),
                textField.trailingAnchor.constraint(equalTo: cellView!.trailingAnchor, constant: -4),
                textField.centerYAnchor.constraint(equalTo: cellView!.centerYAnchor)
            ])
        }
        
        let directory = directories[row]
        cellView?.textField?.stringValue = directory.path
        
        return cellView
    }
    
    func tableView(_ tableView: NSTableView, heightOfRow row: Int) -> CGFloat {
        return 20
    }
}